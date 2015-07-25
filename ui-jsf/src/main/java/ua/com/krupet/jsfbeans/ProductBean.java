package ua.com.krupet.jsfbeans;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.krupet.Product;
import ua.com.krupet.service.ProductService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
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
//@SessionScoped
@ViewScoped
public class ProductBean implements Serializable{

    @Autowired
    private ProductService productService;

    public Product product;
    public Product newProduct = new Product();
    public List<Product> productList;


    @PostConstruct
    private void init() {
        productList = productService.getProductsList();
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
            productList.add(newProduct); // TODO: just anthill lazy loading!
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Posted!", "product posted successfully!");
        } else message = new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Error", "error during posting new product");

        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("success", success);
    }

    public void deleteProduct(Product product) {
        FacesMessage message = null;

        int index = productList.indexOf(product);
        String prodID = product.getId();
        Product deletedProduct = productService.removeProduct(product);
        if (deletedProduct.getId() == null) {
            productList.remove(index); // TODO: just anthill lazy loading!
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

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
