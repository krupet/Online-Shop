package ua.com.krupet.dao;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.com.krupet.BaseDaoTest;
import ua.com.krupet.Product;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by krupet on 8/4/15.
 */
public class ProductsDAOImplTest extends BaseDaoTest{

    @Autowired
    private ProductDAO productDAO;

    @Test
    @Transactional
    public void testPostNewProductAndExpectedISOk() {
        Product postedProduct = productDAO.postProduct(getNewProduct());
        assertNotNull(postedProduct);
        assertNotNull(postedProduct.getId());
    }

    @Test(expected = ConstraintViolationException.class)
//    @Test(expected = Exception.class)
    @Transactional
    public void testPostTwoSameProductsAndExpectedIsException() {

        Product product = getNewProduct();
        Product postedProduct1 = productDAO.postProduct(product);
        assertNotNull(postedProduct1);
        assertNotNull(postedProduct1.getId());

        Product postedProduct2 = productDAO.postProduct(product);
    }

    @Test
    @Transactional
    public void testEditProductAndExpectedIsOK() {

        Product product = getPersistedProduct();

        product.setDescription("updated description");

        Product updatedProduct = productDAO.editProduct(product);
        assertNotNull(updatedProduct);
    }

    @Transactional
    private Product getPersistedProduct() {

        Product postedProduct = productDAO.postProduct(getNewProduct());
        assertNotNull(postedProduct);
        assertNotNull(postedProduct.getId());

        return productDAO.getProductByID(Long.parseLong(postedProduct.getId()));
    }

    @Test
    @Transactional
    public void testGetProductByIDAndExpectedIsOk() {

        Product postedProduct = productDAO.postProduct(getNewProduct());
        assertNotNull(postedProduct);
        assertNotNull(postedProduct.getId());

        Product dbProduct = productDAO.getProductByID(Long.parseLong(postedProduct.getId()));
        assertNotNull(dbProduct);
        assertNotNull(dbProduct.getId());
        assertNotNull(dbProduct.getName());
        assertNotNull(dbProduct.getBrand());
        assertNotNull(dbProduct.getDescription());
        assertNotNull(dbProduct.getPrice());
        assertNotNull(dbProduct.getPictureLink());
        assertNotNull(dbProduct.getCreationDate());
    }

    @Test(expected = Exception.class)
    @Transactional
    public void testGetProductByNonExistentIDAndExpectedIsException() {

        Long productID = 1234567L;
        Product dbProduct = productDAO.getProductByID(productID);
        assertEquals(null, dbProduct);
    }

    @Test
    @Transactional
    public void testGetProductList() {
        productDAO.postProduct(getNewProduct());
        productDAO.postProduct(getNewProduct());
        productDAO.postProduct(getNewProduct());

        List<Product> products = productDAO.getProductsList();
        assertNotNull(products);
        assertEquals(true, products.size() > 3);
    }

    @Test
    @Transactional
    public void testDeleteProductAndExpectedIsOk() {

        Product product = productDAO.postProduct(getNewProduct());

        Product deletedProduct = productDAO.removeProduct(product);
        assertNull(deletedProduct.getId());
    }

    @Test(expected = Exception.class)
    @Transactional
    public void testDeleteProductWithNonExistingIDAndExpectedIsException() {

        Product product = getNewProduct();
        product.setId("1234567");

        Product deletedProduct = productDAO.removeProduct(product);
        assertNull(deletedProduct);
    }

    private Product getNewProduct() {
        /*
            using String.format to avoid constraints collisions in db
         */
        return new Product("",
                           String.format("test_name%d", new Date().getTime()),
                           String.format("test_brand%d", new Date().getTime()),
                           String.format("test_description%d", new Date().getTime()),
                           "20.5",
                           "test_pic_link",
                           "" + new Date().getTime());
    }
}
