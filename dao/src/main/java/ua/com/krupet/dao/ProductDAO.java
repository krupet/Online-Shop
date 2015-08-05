package ua.com.krupet.dao;

import ua.com.krupet.Product;

import java.util.List;

/**
 * Defines interface for products service that are able to retrieve products information from DataBase (DB)
 * Note in service I am using DTO objects instead of entities to avoid some lazy initialization/loading
 * problems with JPA provider
 *
 * @author krupet
 * @see ua.com.krupet.Product
 */
public interface ProductDAO {

    /**
     * Creates new product in DB
     *
     * @param product - DTO object representing new product
     * @return DTO representing persisted product or null if operation failed
     */
    Product postProduct(Product product);

    /**
     * Edits persisted product information
     *
     * @param product - DTO object representing persisted product
     * @return DTO representing persisted product or null if operation failed
     */
    Product editProduct(Product product);

    /**
     * Retrieves product by its ID
     * @param id - product`s id to retrieve
     * @return DTO representing persisted product
     */
    Product getProductByID(Long id);

    /**
     * Retrieve list of all products in DB
     *
     * @return list with products
     */
    List<Product> getProductsList();
}
