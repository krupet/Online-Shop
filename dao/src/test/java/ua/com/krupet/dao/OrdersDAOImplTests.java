package ua.com.krupet.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.com.krupet.*;
import ua.com.krupet.entity.OrderEntity;
import ua.com.krupet.entity.ProductEntity;
import ua.com.krupet.entity.RoleEntity;
import ua.com.krupet.entity.UserEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class OrdersDAOImplTests  extends BaseDaoTest {

    @Autowired
    private OrdersDAO ordersDAO;

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    @Transactional
    public void testPostNewOrderByUserIDAndExpectedIsOk() {

        Long userID = getPersistedUserEntity().getId();
        Order order = new Order();
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setId("" + getPersistedProductEntity().getId());
        Product product2 = new Product();
        product2.setId("" + getPersistedProductEntity().getId());
        Product product3 = new Product();
        product3.setId("" + getPersistedProductEntity().getId());
        products.add(product1);
        products.add(product2);
        products.add(product3);
        order.setProductIDList(products);

        Order persistedOrder = ordersDAO.createOrder(userID, order);
        assertNotNull(persistedOrder);
        assertNotNull(persistedOrder.getId());
    }

    @Test(expected = RuntimeException.class)
    @Transactional
    public void testPostNewOrderByNonExistingUserIDAndExpectedIsException() {

        Long userID = 1234567L;
        Order order = new Order();
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setId("" + getPersistedProductEntity().getId());
        Product product2 = new Product();
        product2.setId("" + getPersistedProductEntity().getId());
        Product product3 = new Product();
        product3.setId("" + getPersistedProductEntity().getId());
        products.add(product1);
        products.add(product2);
        products.add(product3);
        order.setProductIDList(products);

        Order persistedOrder = ordersDAO.createOrder(userID, order);
        assertNull(persistedOrder);
    }

    @Test(expected = RuntimeException.class)
    @Transactional
    public void testPostNewOrderByUserIDWithEmptyProductListAndExpectedIsException() {

        Long userID = getPersistedUserEntity().getId();
        Order order = new Order();
        order.setProductIDList(null);

        Order persistedOrder = ordersDAO.createOrder(userID, order);
        assertNull(persistedOrder);
    }

    @Test
    @Transactional
    public void testPostNewOrderByUsernameAndExpectedIsOk() {

        String userName = getPersistedUserEntity().getLogin();
        Order order = new Order();
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setId("" + getPersistedProductEntity().getId());
        Product product2 = new Product();
        product2.setId("" + getPersistedProductEntity().getId());
        Product product3 = new Product();
        product3.setId("" + getPersistedProductEntity().getId());
        products.add(product1);
        products.add(product2);
        products.add(product3);
        order.setProductIDList(products);

        Order persistedOrder = ordersDAO.createOrderByUsersName(userName, order);
        assertNotNull(persistedOrder);
        assertNotNull(persistedOrder.getId());
    }

    @Test(expected = RuntimeException.class)
    @Transactional
    public void testPostNewOrderByNonExistingUsernameAndExpectedIsException() {

        String userName = "non_existing";
        Order order = new Order();
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setId("" + getPersistedProductEntity().getId());
        Product product2 = new Product();
        product2.setId("" + getPersistedProductEntity().getId());
        Product product3 = new Product();
        product3.setId("" + getPersistedProductEntity().getId());
        products.add(product1);
        products.add(product2);
        products.add(product3);
        order.setProductIDList(products);

        Order persistedOrder = ordersDAO.createOrderByUsersName(userName, order);
        assertNull(persistedOrder);
    }

    @Test(expected = RuntimeException.class)
    @Transactional
    public void testPostNewOrderByUsernameWithEmptyProductListAndExpectedIsException() {

        Long userID = getPersistedUserEntity().getId();
        Order order = new Order();
        order.setProductIDList(null);

        Order persistedOrder = ordersDAO.createOrder(userID, order);
        assertNull(persistedOrder);
    }

    @Transactional
    private UserEntity getPersistedUserEntity() {
        /*
            setup for test - boiler plate code
         */
        Session session = sessionFactory.getCurrentSession();
        UserEntity userEntity = getNewUSerEntity();
        RoleEntity roleEntity = new RoleEntity(userEntity.getLogin(), RoleTypes.ROLE_USER);
        userEntity.setRole(roleEntity);
        Long userID = (Long) session.save(userEntity);
        session.save(roleEntity);
        userEntity.setId(userID);
        return userEntity;
    }

    @Transactional
    private ProductEntity getPersistedProductEntity() {
        Session session = sessionFactory.getCurrentSession();
        ProductEntity productEntity = getNewProductEntity();
        Long productID = (Long) session.save(productEntity);
        productEntity.setId(productID);
        return productEntity;
    }

    public UserEntity getNewUSerEntity() {

        String userName = String.format("test_login%d", new Date().getTime());
        return new UserEntity("test_firstName",
                "test_lastName",
                String.format("test_email%d@gmail.com", new Date().getTime()),
                "test_age",
                "test_postCode",
                "test_address",
                new Date().getTime(),
                userName,
                "test_password",
                null,
                null);
    }

    private ProductEntity getNewProductEntity() {
        return new ProductEntity(String.format("test_name%d", new Date().getTime()),
                String.format("test_brand%d", new Date().getTime()),
                String.format("test_description%d", new Date().getTime()),
                new BigDecimal(20.5),
                "test_pic_link",
                new Date().getTime());
    }

    private OrderEntity getNewOrderEntity() {

        Long userID = getPersistedUserEntity().getId();
        OrderEntity orderEntity = new OrderEntity(new Date().getTime(), OrderStatus.ACCEPTED);

        return null;
    }
}
