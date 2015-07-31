package ua.com.krupet.jsfbeans;

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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
}
