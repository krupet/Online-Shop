package ua.com.krupet.jsfbeans;

import org.springframework.beans.factory.annotation.Autowired;
import ua.com.krupet.Product;
import ua.com.krupet.service.ProductService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
        System.out.println("creating product");
        System.out.println(productService.postProduct(product));
    }

    public List<Product> getProductsList() {
        return productService.getProductsList();
    }
}
