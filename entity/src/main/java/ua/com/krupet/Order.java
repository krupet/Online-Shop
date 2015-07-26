package ua.com.krupet;

import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.List;

/**
 * Created by krupet on 11.07.2015.
 */
@ManagedBean
public class Order implements Serializable{

    private String id;
    private String creationDate;
    private String orderStatus;

    private User customer;
    private List<Long> productList;

    public Order(String id, String creationDate, String orderStatus, User customer, List<Long> productList) {
        this.id = id;
        this.creationDate = creationDate;
        this.orderStatus = orderStatus;
        this.customer = customer;
        this.productList = productList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public List<Long> getProductList() {
        return productList;
    }

    public void setProductList(List<Long> productList) {
        this.productList = productList;
    }
}
