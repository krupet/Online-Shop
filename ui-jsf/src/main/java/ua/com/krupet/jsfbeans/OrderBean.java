package ua.com.krupet.jsfbeans;

import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.krupet.Order;
import ua.com.krupet.Product;
import ua.com.krupet.User;
import ua.com.krupet.jsfbeans.util.LazyOrderDataModel;
import ua.com.krupet.service.OrdersService;
import ua.com.krupet.service.ProductService;
import ua.com.krupet.service.UserService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by krupet on 7/30/15.
 */
@ManagedBean
@SessionScoped
public class OrderBean implements Serializable{

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    private LazyDataModel<Order> lazyDataModel;
    private String customerID;
    private User user;
    private Order order = new Order();
    private List<Product> products = new ArrayList<>();
    private Order selectedOrder;
    private String orderStatus = "";

    @PostConstruct
    public void init() {
        lazyDataModel = new LazyOrderDataModel(ordersService);
    }

    public LazyDataModel<Order> getLazyDataModel() {

        return lazyDataModel;
    }

    public void setUser() {

        if (customerID != null) {
            user = userService.getUserByID(Long.parseLong(customerID));
        } else user = new User();
    }



    public User getUser() {
        return user;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<Product> getProducts() {

        if (order != null) {
            return products = order.getProductIDList();
        } else return new ArrayList<>();
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Order getSelectedOrder() {
        return selectedOrder;
    }

    public void setSelectedOrder(Order selectedOrder) {
        this.selectedOrder = selectedOrder;
    }

    public void editOrderStatus(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean success = false;

        Long orderID = Long.parseLong(selectedOrder.getId());

        selectedOrder.setOrderStatus(orderStatus);

        if (ordersService.updateOrder(selectedOrder) != null) {
            success = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Updated!", "order with ID("+ orderID + ") updated successfully!");
        } else message = new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Error", "error during updating order with ID(" + orderID + ")!");

        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("success", success);
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
