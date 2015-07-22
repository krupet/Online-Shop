package ua.com.krupet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.com.krupet.Product;
import ua.com.krupet.dao.ProductDAO;
import ua.com.krupet.service.ProductService;

import java.util.List;

/**
 * Created by krupet on 7/22/15.
 */
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductDAO productDAO;

    @Override
    @Transactional
    public Product postProduct(Product product) {
        return productDAO.postProduct(product);
    }

    @Override
    @Transactional
    public List<Product> getProductsList() {
        return productDAO.getProductsList();
    }
}
