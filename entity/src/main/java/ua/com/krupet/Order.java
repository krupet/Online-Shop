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

    private String customerID;
    private List<Long> productIDList;

    public Order() {
    }

    public Order(String id) {
        this.id = id;
    }

    public Order(String id, String creationDate, String orderStatus, String customerID, List<Long> productIDList) {
        this.id = id;
        this.creationDate = creationDate;
        this.orderStatus = orderStatus;
        this.customerID = customerID;
        this.productIDList = productIDList;
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

    public String getCustomer() {
        return customerID;
    }

    public void setCustomer(String customerID) {
        this.customerID = customerID;
    }

    public List<Long> getProductIDList() {
        return productIDList;
    }

    public void setProductIDList(List<Long> productList) {
        this.productIDList = productList;
    }
}
