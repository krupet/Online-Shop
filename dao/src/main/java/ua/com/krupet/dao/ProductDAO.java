package ua.com.krupet.dao;

import ua.com.krupet.Product;

import java.util.List;

/**
 * Created by krupet on 7/22/15.
 */
public interface ProductDAO {

    Product postProduct(Product product);
    List<Product> getProductsList();
}
