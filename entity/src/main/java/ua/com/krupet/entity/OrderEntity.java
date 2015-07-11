package ua.com.krupet.entity;

import ua.com.krupet.OrderStatus;
import ua.com.krupet.Product;

import javax.persistence.*;
import java.util.List;

/**
 * Created by krupet on 11.07.2015.
 */

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_creation_date")
    private Long creationDate;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
//    private String dispatchAddress;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity customer;

    @OneToMany(mappedBy = "order")
    private List<ProductEntity> productList;

    public OrderEntity() {
    }

    public OrderEntity(Long creationDate, OrderStatus status, UserEntity customer, List<ProductEntity> productList) {
        this.creationDate = creationDate;
        this.status = status;
        this.customer = customer;
        this.productList = productList;
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

    public List<ProductEntity> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductEntity> productList) {
        this.productList = productList;
    }
}
