package ua.com.krupet.dao.impl;

import ua.com.krupet.dao.ProductDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by krupet on 7/22/15.
 */
public class ProductDAOImpl implements ProductDAO{
    @Override
    public List<String> getProductsList() {
        List<String> products = new ArrayList<>();
        products.add("GUN");
        products.add("Very BIG GUN");
        products.add("HAT");
        products.add("BELT");
        products.add("GATLING MACHINE GUN");

        return products;
    }
}
