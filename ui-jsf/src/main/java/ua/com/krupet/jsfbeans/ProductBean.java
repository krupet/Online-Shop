package ua.com.krupet.jsfbeans;

import org.springframework.beans.factory.annotation.Autowired;
import ua.com.krupet.Product;
import ua.com.krupet.service.ProductService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.List;

/**
 * Created by krupet on 7/22/15.
 */

@ManagedBean
@SessionScoped
public class ProductBean {

    @Autowired
    private ProductService productService;

    public void postProduct(Product product) {
        String statusMessage = null;
        Product postedProduct = productService.postProduct(product);
        statusMessage = (postedProduct != null) ? "product posted successfully!"
                                                : "sorry something went wrong =(";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(statusMessage));
    }

    public List<Product> getProductsList() {
        return productService.getProductsList();
    }
}
