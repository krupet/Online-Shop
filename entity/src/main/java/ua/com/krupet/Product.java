package ua.com.krupet;

import javax.faces.bean.ManagedBean;
import java.io.Serializable;

/**
 * DTO representing some product
 *
 * @author krupet
 */

@ManagedBean
public class Product implements Serializable{

    private String id;
    private String name;
    private String brand;
    private String description;
    private String price;
    // will be used for picture of a product
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != null ? !id.equals(product.id) : product.id != null) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (brand != null ? !brand.equals(product.brand) : product.brand != null) return false;
        if (description != null ? !description.equals(product.description) : product.description != null) return false;
        if (price != null ? !price.equals(product.price) : product.price != null) return false;
        if (pictureLink != null ? !pictureLink.equals(product.pictureLink) : product.pictureLink != null) return false;
        return !(creationDate != null ? !creationDate.equals(product.creationDate) : product.creationDate != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (pictureLink != null ? pictureLink.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", pictureLink='" + pictureLink + '\'' +
                ", creationDate='" + creationDate + '\'' +
                '}';
    }
}
