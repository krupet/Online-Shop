package ua.com.krupet.jsfbeans;

import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.krupet.Product;
import ua.com.krupet.jsfbeans.util.LazyProductDataModel;
import ua.com.krupet.service.ProductService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by krupet on 7/22/15.
 */

@ManagedBean
@SessionScoped
//@ViewScoped
public class ProductBean implements Serializable{

    @Autowired
    private ProductService productService;

    public Product product;
    public Product newProduct = new Product();
    public List<Product> productList;
    public LazyDataModel<Product> lazyDataModel;

    @PostConstruct
    private void init() {
        productList = productService.getProductsList();
        lazyDataModel = new LazyProductDataModel(productService);
    }

    public void editProduct(ActionEvent event) {

        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean success = false;

        if (productService.editProduct(product) != null) {
            success = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Updated!", "product with ID (" + product.getId() + ") updated successfully!");
        } else message = new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Error", "error during updating product with ID (" + product.getId() + ")");

        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("success", success);
    }

    public void addProduct(ActionEvent event) {

        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean success = false;

        newProduct.setPictureLink("pick");
        newProduct.setCreationDate("" + new Date().getTime());
        if (productService.postProduct(newProduct) != null) {
            success = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Posted!", "product posted successfully!");
        } else message = new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Error", "error during posting new product");

        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("success", success);
    }

    public void deleteProduct(Product product) {
        FacesMessage message = null;

        String prodID = product.getId();
        Product deletedProduct = productService.removeProduct(product);
        if (deletedProduct.getId() == null) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Deleted!", "product with ID (" + prodID + ") deleted successfully!");
        } else message = new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Error", "error during removing product with ID (" + prodID + ")");

        FacesContext.getCurrentInstance().addMessage(null, message);
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

    public LazyDataModel<Product> getLazyDataModel() {
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<Product> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }
}
