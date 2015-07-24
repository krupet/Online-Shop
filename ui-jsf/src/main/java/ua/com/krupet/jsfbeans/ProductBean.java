package ua.com.krupet.jsfbeans;

import org.springframework.beans.factory.annotation.Autowired;
import ua.com.krupet.Product;
import ua.com.krupet.service.ProductService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.List;

/**
 * Created by krupet on 7/22/15.
 */

@ManagedBean
//@SessionScoped
@ViewScoped
public class ProductBean {

    @Autowired
    private ProductService productService;

    public Product product;
    public boolean edit;
    public List<Product> productList;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @PostConstruct
    private void init() {
        productList = productService.getProductsList();
    }

    public void postProduct(Product product) {
        String statusMessage = null;
        Product postedProduct = productService.postProduct(product);
        statusMessage = (postedProduct != null) ? "prod posted successfully!"
                                                : "sorry something went wrong =(";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(statusMessage));
    }

    public void edit(Product product) {
        this.product = product;
        edit = true;
    }

    public void editProduct(Product prod) {

        String statusMessage = null;
        prod.setId(product.getId());
        Product editedProduct = productService.editProduct(prod);
        statusMessage = (editedProduct != null) ? "prod updated successfully!"
                                                : "sorry something went wrong =(";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(statusMessage));
    }

    public Product getProductByID(Long productID) {

        String statusMessage = null;
        Product retrievedProduct = productService.getProductByID(productID);
        statusMessage = (retrievedProduct != null) ? "prod posted successfully!"
                                                   : "sorry something went wrong =(";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(statusMessage));
        return retrievedProduct;
    }

    public List<Product> getProductsList() {
        return productList;
    }

    public void removeProduct(Product product) {

        String statusMessage = null;
        Product removedProduct = productService.removeProduct(product);
        statusMessage = (removedProduct.getId() == null) ? "prod removed successfully"
                                                         : "sorry something went wrong =(";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(statusMessage));
    }
}
