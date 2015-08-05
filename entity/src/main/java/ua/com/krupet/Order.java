package ua.com.krupet;

import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.List;

/**
 * DTO representing order.
 *
 * @author krupet
 */
@ManagedBean
public class Order implements Serializable {

    private String id;
    private String creationDate;
    private String orderStatus;

    private String customerID;
    //List of products witch belongs to this order
    private List<Product> productIDList;

    public Order() {
    }

    public Order(String id) {
        this.id = id;
    }

    public Order(String id, String creationDate, String orderStatus, String customerID, List<Product> productIDList) {
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

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public List<Product> getProductIDList() {
        return productIDList;
    }

    public void setProductIDList(List<Product> productList) {
        this.productIDList = productList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != null ? !id.equals(order.id) : order.id != null) return false;
        if (creationDate != null ? !creationDate.equals(order.creationDate) : order.creationDate != null) return false;
        if (orderStatus != null ? !orderStatus.equals(order.orderStatus) : order.orderStatus != null) return false;
        return !(customerID != null ? !customerID.equals(order.customerID) : order.customerID != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        result = 31 * result + (customerID != null ? customerID.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", customerID='" + customerID + '\'' +
                ", productIDList=" + productIDList +
                '}';
    }
}
