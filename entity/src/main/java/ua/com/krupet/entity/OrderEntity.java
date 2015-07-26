package ua.com.krupet.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ua.com.krupet.OrderStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by krupet on 11.07.2015.
 */

@Entity
@Table(name = "orders")
public class OrderEntity implements Serializable{

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_creation_date")
    private Long creationDate;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity customer;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "order_products", joinColumns = @JoinColumn(name = "order_id"))
    @Column(name = "product_id")
    private List<Long> productIDList = new ArrayList<>();

    public OrderEntity() {
    }

    public OrderEntity(Long creationDate, OrderStatus status) {
        this.creationDate = creationDate;
        this.status = status;
    }

    public OrderEntity(Long creationDate, OrderStatus status, UserEntity customer) {
        this.creationDate = creationDate;
        this.status = status;
        this.customer = customer;
    }

    public OrderEntity(Long creationDate, OrderStatus status, UserEntity customer, List<Long> productIDList) {
        this.creationDate = creationDate;
        this.status = status;
        this.customer = customer;
        this.productIDList = productIDList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public UserEntity getCustomer() {
        return customer;
    }

    public void setCustomer(UserEntity customer) {
        this.customer = customer;
    }

    public List<Long> getProductIDList() {
        return productIDList;
    }

    public void setProductIDList(List<Long> productIDList) {
        this.productIDList = productIDList;
    }
}
