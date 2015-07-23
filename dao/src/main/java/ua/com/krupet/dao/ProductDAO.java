package ua.com.krupet.dao;

import ua.com.krupet.Product;

import java.util.List;

/**
 * Created by krupet on 7/22/15.
 */
public interface ProductDAO {

    Product postProduct(Product product);
    Product editProduct(Product product);
    Product getProductByID(Long id);
    List<Product> getProductsList();
    Product removeProduct(Product product);
}
