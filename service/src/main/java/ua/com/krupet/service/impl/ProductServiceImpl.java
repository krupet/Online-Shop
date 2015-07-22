package ua.com.krupet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<String> getProductsList() {
        return productDAO.getProductsList();
    }
}
