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

    @Override
    public Product editProduct(Product product) {

        Long productID = Long.parseLong(product.getId());

        Session session = sessionFactory.getCurrentSession();
        ProductEntity productEntity = new ProductEntity(
                product.getName(),
                product.getBrand(),
                product.getDescription(),
                new BigDecimal(product.getPrice()),
                product.getPictureLink(),
                Long.parseLong(product.getCreationDate()));

        productEntity.setId(productID);


        session.update(productEntity);
        session.flush();

        /*
            check is it updated
         */
        ProductEntity dbProduct = (ProductEntity) session.get(ProductEntity.class, productID);
        if (dbProduct == null) throw new RuntimeException("internal server error while updating product with id: " + productID);

        return new Product(dbProduct.getId().toString(),
                           dbProduct.getName(),
                           dbProduct.getBrand(),
                           dbProduct.getDescription(),
                           dbProduct.getPrice().toPlainString(),
                           dbProduct.getPictureLink(),
                           dbProduct.getCreationDate().toString());
    }

    @Override
    public Product getProductByID(Long id) {

        Session session = sessionFactory.getCurrentSession();
        ProductEntity dbProduct = (ProductEntity) session.get(ProductEntity.class, id);
        if (dbProduct == null) throw new RuntimeException("error - there is no product with such id: " + id);

        return new Product(dbProduct.getId().toString(),
                           dbProduct.getName(),
                           dbProduct.getBrand(),
                           dbProduct.getDescription(),
                           dbProduct.getPrice().toPlainString(),
                           dbProduct.getPictureLink(),
                           dbProduct.getCreationDate().toString());
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Product> getProductsList() {

        Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(ProductEntity.class);
        List<ProductEntity> productEntities = criteria.list();


        return productEntities.stream().map(
                prodEnt -> new Product(prodEnt.getId().toString(),
                                       prodEnt.getName(),
                                       prodEnt.getBrand(),
                                       prodEnt.getDescription(),
                                       prodEnt.getPrice().toPlainString(),
                                       prodEnt.getPictureLink(),
                                       prodEnt.getCreationDate().toString())).collect(Collectors.toList());
    }

    @Override
    public Product removeProduct(Product product) {

        Long productID = Long.parseLong(product.getId());

        Session session = sessionFactory.getCurrentSession();

        /*
            trying to check whether this entity exists in DB
         */
        ProductEntity dbProduct = (ProductEntity) session.get(ProductEntity.class, productID);
        if (dbProduct == null) throw new RuntimeException("error: there is no such product in db!");
        session.delete(dbProduct);
        session.flush();

        /*
            check: does product actually removed
         */
        ProductEntity deletedProduct = (ProductEntity) session.get(ProductEntity.class, productID);
        if (deletedProduct != null) throw new RuntimeException("internal server error: delete product with id: " + productID);
        return new Product();
    }
}
