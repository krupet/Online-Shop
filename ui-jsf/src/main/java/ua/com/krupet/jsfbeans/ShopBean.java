package ua.com.krupet.jsfbeans;

import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ua.com.krupet.Order;
import ua.com.krupet.OrderStatus;
import ua.com.krupet.Product;
import ua.com.krupet.jsfbeans.util.LazyProductDataModel;
import ua.com.krupet.service.OrdersService;
import ua.com.krupet.service.ProductService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by krupet on 8/1/15.
 */

@ManagedBean
@SessionScoped
public class ShopBean implements Serializable {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrdersService ordersService;

    private LazyDataModel<Product> lazyDataModel;
    private Order customersOrder = new Order();
    private String userName;
    private String widgetVar;

    @PostConstruct
    private void init() {
        lazyDataModel = new LazyProductDataModel(productService);
        customersOrder.setCreationDate("" + new Date().getTime());
        customersOrder.setProductIDList(new ArrayList<>());
    }

    public LazyDataModel<Product> getLazyDataModel() {
        return lazyDataModel;
    }

    public String getUserName() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        userName = auth.getName(); //get logged in username
        return userName;
    }

    public void addProductToCart(Product product) {
        customersOrder.getProductIDList().add(product);
    }

    public void removeProductFromCart(Product product) {
        customersOrder.getProductIDList().remove(product);
    }

    public Order getCustomersOrder() {
        return customersOrder;
    }

    public void setCustomersOrder(Order customersOrder) {
        this.customersOrder = customersOrder;
    }

    public int getTotal() {

        BigDecimal total = new BigDecimal(0);
        for (Product product : customersOrder.getProductIDList()) {
            total = total.add(new BigDecimal(product.getPrice()));
        }

        return total.intValue();
    }

    public void createOrder() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean success = false;

        customersOrder.setOrderStatus(OrderStatus.PREPARING.toString());

        if (ordersService.createOrderByUsersName(getUserName(), customersOrder) != null) {
            success = true;
            customersOrder.getProductIDList().clear();
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Accepted!", "Your order accepted successfully!");
        } else message = new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Error", "error during accepting order!");

        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("success", success);
        context.addCallbackParam("widget", widgetVar);
    }

    public String getWidgetVar() {
        return widgetVar;
    }

    public void setWidgetVar(String widgetVar) {
        this.widgetVar = widgetVar;
    }
}
