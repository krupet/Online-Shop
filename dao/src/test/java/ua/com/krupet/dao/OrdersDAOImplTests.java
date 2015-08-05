package ua.com.krupet.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.com.krupet.BaseDaoTest;
import ua.com.krupet.Order;
import ua.com.krupet.OrderStatus;
import ua.com.krupet.Product;
import ua.com.krupet.RoleTypes;
import ua.com.krupet.entity.OrderEntity;
import ua.com.krupet.entity.ProductEntity;
import ua.com.krupet.entity.RoleEntity;
import ua.com.krupet.entity.UserEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * tests for ua.com.krupet.dao.impl.OrdersDAOImpl
 */
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

    @Test
    @Transactional
    public void testGetOrderByOrderIDAndExpectedIsOk() {

        Long orderID = getPersistedOrderEntity().getId();

        Order order = ordersDAO.getOrderByID(orderID);
        assertNotNull(order);
        assertNotNull(order.getId());
        assertNotNull(order.getCreationDate());
        assertNotNull(order.getOrderStatus());
    }

    @Test(expected = RuntimeException.class)
    @Transactional
    public void testGetOrderByNonExistingOrderIDAndExpectedIsException() {

        Long orderID = 1234567L;

        Order order = ordersDAO.getOrderByID(orderID);
        assertNotNull(order);
        assertNotNull(order.getId());
        assertNotNull(order.getCreationDate());
        assertNotNull(order.getOrderStatus());
    }

    @Test
    @Transactional
    public void testUpdateOrderStatusAndExpectedIsOk() {

        Long orderID = getPersistedOrderEntity().getId();

        Order order = new Order();
        order.setId(orderID.toString());
        order.setOrderStatus(OrderStatus.DELIVERED.toString());

        Order updatedOrder = ordersDAO.updateOrderStatus(order);
        assertNotNull(updatedOrder);
        assertNotNull(updatedOrder.getId());
    }

    @Test(expected = RuntimeException.class)
    @Transactional
    public void testUpdateOrderStatusWithNotExistingIDAndExpectedIsException() {

        Long orderID = 1234567L;

        Order order = new Order();
        order.setId(orderID.toString());
        order.setOrderStatus(OrderStatus.DELIVERED.toString());

        Order updatedOrder = ordersDAO.updateOrderStatus(order);
        assertNull(updatedOrder);
    }

    @Test
    @Transactional
    public void testGetOrdersListAndExpectedIsOk() {

        getPersistedOrderEntity();
        getPersistedOrderEntity();
        getPersistedOrderEntity();

        List<Order> orders = ordersDAO.getOrdersList();
        assertNotNull(orders);
        assertTrue(orders.size() > 2);
    }

    @Test
    @Transactional
    public void testGetOrderListByUserIDAndExpectedIsOk() {

        Long userID = getPersistedOrderEntity().getCustomer().getId();

        List<Order> orders = ordersDAO.getOrdersListByUserID(userID);
        assertNotNull(orders);
        assertTrue(orders.size() == 1);
    }

    @Test(expected = RuntimeException.class)
    @Transactional
    public void testGetOrderListByNonExistingUserIDAndExpectedIsException() {

        Long userID = 1234567L;

        List<Order> orders = ordersDAO.getOrdersListByUserID(userID);
        assertNull(orders);
    }

    @Test
    @Transactional
    public void testDeleteOrderAndExpectedIsOk() {

        Long orderID = getPersistedOrderEntity().getId();
        Order order = new Order(orderID.toString());

        Order deletedOrder = ordersDAO.deleteOrder(order);
        assertNotNull(deletedOrder);
        assertNull(deletedOrder.getId());
    }

    @Test(expected = RuntimeException.class)
    @Transactional
    public void testDeleteOrderWithNonExistingIDAndExpectedIsException() {

        Long orderID = 1234567L;
        Order order = new Order(orderID.toString());

        Order deletedOrder = ordersDAO.deleteOrder(order);
        assertNotNull(deletedOrder);
        assertNull(deletedOrder.getId());
    }

    /*
        methods needed only for test setups
     */
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

    @Transactional
    private OrderEntity getPersistedOrderEntity() {

        Session session = sessionFactory.getCurrentSession();
        OrderEntity orderEntity = getNewOrderEntity();
        Long orderID = (Long) session.save(orderEntity);
        orderEntity.setId(orderID);
        return orderEntity;
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

        UserEntity userEntity = getPersistedUserEntity();
        return new OrderEntity(new Date().getTime(), OrderStatus.ACCEPTED, userEntity, null);
    }
}
