package ua.com.krupet.entity;

import ua.com.krupet.OrderStatus;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * EntityClass that represents some order
 *
 * @author krupet
 * @see ua.com.krupet.OrderStatus
 * @see ua.com.krupet.entity.ProductEntity
 */

@Entity
@Table(name = "ORDERS")
public class OrderEntity implements Serializable{

    @Id
    @Column(name = "ORDER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ORDER_CREATION_DATE")
    private Long creationDate;

    @Column(name = "ORDER_STATUS")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private UserEntity customer;

    // list of orders that belongs to order
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ORDER_PRODUCTS", joinColumns = @JoinColumn(name = "ORDER_ID"),
                                        inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
    private List<ProductEntity> productIDList = new ArrayList<>();

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

    public OrderEntity(Long creationDate, OrderStatus status, UserEntity customer, List<ProductEntity> productIDList) {
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

    public List<ProductEntity> getProductIDList() {
        return productIDList;
    }

    public void setProductIDList(List<ProductEntity> productIDList) {
        this.productIDList = productIDList;
    }
}
