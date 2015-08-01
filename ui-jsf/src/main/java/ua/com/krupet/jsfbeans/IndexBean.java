package ua.com.krupet.jsfbeans;

import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.krupet.Product;
import ua.com.krupet.jsfbeans.util.LazyProductDataModel;
import ua.com.krupet.service.ProductService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.PrimitiveIterator;

/**
 * Created by krupet on 8/1/15.
 */
@ManagedBean
@SessionScoped
public class IndexBean implements Serializable {

    @Autowired
    private ProductService productService;

    private LazyDataModel<Product> lazyDataModel;

    @PostConstruct
    private void init() {
        lazyDataModel = new LazyProductDataModel(productService);
    }

    public LazyDataModel<Product> getLazyDataModel() {
        return lazyDataModel;
    }
}
