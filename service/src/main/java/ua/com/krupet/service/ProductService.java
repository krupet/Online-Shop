package ua.com.krupet.service;

import ua.com.krupet.Product;

import java.util.List;

/**
 * Created by krupet on 7/21/15.
 */
public interface ProductService {

    Product postProduct(Product product);
    List<Product> getProductsList();
}
