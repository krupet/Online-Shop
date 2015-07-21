package ua.com.krupet.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by krupet on 11.07.2015.
 */

@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @Column(name = "product_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", nullable = false)
    private String name;

    @Column(name = "product_brand", nullable = false)
    private String brand;

    @Column(name = "product_description", nullable = false)
    private String description;

    @Column(name = "product_price", nullable = false)
    private BigDecimal price;

    @Column(name = "product_pic", nullable = true)
    private String pictureLink;

    @Column(name = "product)creation_time", nullable = false)
    private Long creationDate;

    public ProductEntity() {
    }

    public ProductEntity(String name, String brand, String description, BigDecimal price, String pictureLink, Long creationDate) {
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.price = price;
        this.pictureLink = pictureLink;
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }

    public Long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }
}
