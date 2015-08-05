package ua.com.krupet.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.math.BigDecimal;

/**
 * EntityClass representing some product
 *
 * @author krupet
 */

@Entity
/*
    column names MUST! to be quoted because of ...setProperty("hibernate.globally_quoted_identifiers", "true");...
    for more info look here: http://stackoverflow.com/questions/25183017/org-hibernate-annotationexception-unable-to-create-unique-key-constraint
 */
@Table(name = "PRODUCTS", uniqueConstraints = @UniqueConstraint(columnNames = {"`PRODUCT_NAME`", "`PRODUCT_BRAND`"}))
public class ProductEntity {

    @Id
    @Column(name = "PRODUCT_ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PRODUCT_NAME", nullable = false)
    private String name;

    @Column(name = "PRODUCT_BRAND", nullable = false)
    private String brand;

    @Column(name = "PRODUCT_DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "PRODUCT_PRICE", nullable = false)
    private BigDecimal price;

    @Column(name = "PRODUCT_PIC", nullable = true)
    private String pictureLink;

    @Column(name = "PRODUCT_CREATION_TIME", nullable = false)
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
