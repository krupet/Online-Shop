package ua.com.krupet;

import javax.faces.bean.ManagedBean;

/**
 * Created by krupet on 11.07.2015.
 */

@ManagedBean
public class Product {

    private String id;
    private String name;
    private String brand;
    private String description;
    private String price;
    private String pictureLink;
    private String creationDate;

    public Product() {
    }

    public Product(String id, String name, String brand, String description,
                   String price, String pictureLink, String creationDate) {

        this.id = id;
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.price = price;
        this.pictureLink = pictureLink;
        this.creationDate = creationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}
