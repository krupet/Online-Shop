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
 * Basic implementation of ProductDao interface.
 *
 * Such complexity is explained by using DTO objects to avoid lazy loading/initialization problems
 *
 * @author krupet
 * @see ua.com.krupet.Product
 * @see ua.com.krupet.dao.ProductDAO
 */
public class ProductDAOImpl implements ProductDAO{

    /*
        TODO: constraint violation handling
     */

    @Autowired
    private SessionFactory sessionFactory;


    /**
     * Creates new product in DB
     *
     * @param product - DTO object representing new product
     * @return DTO representing persisted product or null if operation failed
     */
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

    /**
     * Edits persisted product information
     *
     * @param product - DTO object representing persisted product
     * @return DTO representing persisted product or null if operation failed
     */
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


//        session.update(productEntity);
        session.merge(productEntity);
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

    /**
     * Retrieves product by its ID
     * @param id - product`s id to retrieve
     * @return DTO representing persisted product
     */
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

    /**
     * Retrieve list of all products in DB
     *
     * @return list with products
     */
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
}
