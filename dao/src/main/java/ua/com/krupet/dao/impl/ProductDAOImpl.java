package ua.com.krupet.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.krupet.Product;
import ua.com.krupet.dao.ProductDAO;
import ua.com.krupet.entity.ProductEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by krupet on 7/22/15.
 */
public class ProductDAOImpl implements ProductDAO{

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public Product postProduct(Product product) {
        ProductEntity productEntity = new ProductEntity(
                product.getName(),
                product.getBrand(),
                product.getDescription(),
                new BigDecimal(product.getPrice()),
                product.getPictureLink(),
                Long.parseLong(product.getCreationDate()));

        Session session = sessionFactory.getCurrentSession();
        Long productID = (Long) session.save(productEntity);

        if (productID != null) {
            product.setId(productID.toString());
            return product;
        } else return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Product> getProductsList() {

        Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(ProductEntity.class);
        List<ProductEntity> productEntities = criteria.list();


        List<Product> products = productEntities.stream().map(
                prodEnt -> new Product(prodEnt.getId().toString(),
                                       prodEnt.getName(),
                                       prodEnt.getBrand(),
                                       prodEnt.getDescription(),
                                       prodEnt.getPrice().toPlainString(),
                                       prodEnt.getPictureLink(),
                                       prodEnt.getCreationDate().toString()))
                .collect(Collectors.toList());

        return products;
    }
}
