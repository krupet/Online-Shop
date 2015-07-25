package ua.com.krupet.jsfbeans;

import org.springframework.beans.factory.annotation.Autowired;
import ua.com.krupet.Product;
import ua.com.krupet.service.ProductService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

/**
 * Created by krupet on 7/22/15.
 */

@ManagedBean
//@SessionScoped
@ViewScoped
public class ProductBean implements Serializable{

    @Autowired
    private ProductService productService;

    public Product product;
    public Product newProduct;
    public List<Product> productList;


    @PostConstruct
    private void init() {
        productList = productService.getProductsList();
    }

    public void editProduct(Product prod) {

        String statusMessage = null;
        prod.setId(product.getId());
        Product editedProduct = productService.editProduct(prod);
        statusMessage = (editedProduct != null) ? "prod updated successfully!"
                                                : "sorry something went wrong =(";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(statusMessage));
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getNewProduct() {
        return newProduct;
    }

    public void setNewProduct(Product newProduct) {
        this.newProduct = newProduct;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
