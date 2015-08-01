package ua.com.krupet.jsfbeans;

import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ua.com.krupet.Order;
import ua.com.krupet.Product;
import ua.com.krupet.jsfbeans.util.LazyProductDataModel;
import ua.com.krupet.service.ProductService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by krupet on 8/1/15.
 */

@ManagedBean
@SessionScoped
public class ShopBean implements Serializable {

    @Autowired
    private ProductService productService;

    private LazyDataModel<Product> lazyDataModel;
    private Order customersOrder = new Order();

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
        return auth.getName(); //get logged in username
    }

    public void addProductToCart(Product product) {
        customersOrder.getProductIDList().add(product);
    }

    public Order getCustomersOrder() {
        return customersOrder;
    }

    public void setCustomersOrder(Order customersOrder) {
        this.customersOrder = customersOrder;
    }
}
