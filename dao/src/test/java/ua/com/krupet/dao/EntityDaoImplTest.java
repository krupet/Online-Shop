package ua.com.krupet.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.com.krupet.BaseDaoTest;
import ua.com.krupet.Order;
import ua.com.krupet.OrderStatus;
import ua.com.krupet.RoleTypes;
import ua.com.krupet.entity.OrderEntity;
import ua.com.krupet.entity.RoleEntity;
import ua.com.krupet.entity.UserEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by krupet on 04.07.2015.
 */
public class EntityDaoImplTest extends BaseDaoTest{

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void entityTest() {
        UserEntity user = new UserEntity("firstName", "lastName", "email" + new Date().getTime(), "1L", "postCode",
                "address", new Date().getTime(),"login", "password", null, null);

        RoleEntity role = new RoleEntity(user.getLogin(), RoleTypes.ROLE_USER);
        user.setRole(role);
        role.setUser(user);

        OrderEntity orderEntity1 = new OrderEntity(new Date().getTime(), OrderStatus.PREPARING, user);
        OrderEntity orderEntity2 = new OrderEntity(new Date().getTime(), OrderStatus.PREPARING, user);

        List<OrderEntity> orderList = new ArrayList<>();
        orderList.add(orderEntity1);
        orderList.add(orderEntity2);

        user.setOrders(orderList);

        Session session = sessionFactory.openSession();
        Long customerID = (Long) session.save(user);
        Long orderID1 = (Long) session.save(orderEntity1);
        Long orderID2 = (Long) session.save(orderEntity2);
        session.close();
        System.out.println(customerID);
        System.out.println(orderID1);
        System.out.println(orderID2);
    }

    @Test
    public void getOrdersListTest() {
        Session session = sessionFactory.openSession();

        UserEntity user = (UserEntity) session.load(UserEntity.class, 1L);

        List<OrderEntity> orders = user.getOrders();

        OrderEntity order1 = orders.get(0);
        System.out.println(order1.getProductIDList());
        List<Long> products = order1.getProductIDList();
        products.add(123L);
        products.add(34235L);
        products.add(234426546L);

        order1.setProductIDList(products);
        System.out.println(order1.getId());
        session.save(order1);
        session.flush();
        OrderEntity dbOrder = (OrderEntity) session.get(OrderEntity.class, 5L);
        System.out.println(dbOrder.getProductIDList());

//        for (OrderEntity order: orders) {
//            System.out.println(order.getId());
//            System.out.println(order.getStatus());
//        }
        session.close();
    }

    @Test
    public void testOrderProductsList() {
        Session session = sessionFactory.openSession();

        OrderEntity dbOrder = (OrderEntity) session.get(OrderEntity.class, 5L);
        System.out.println(dbOrder.getProductIDList());

        session.close();
    }

    @Test
    public void bigTest() {

        /*
            Phase 1
         */
        UserEntity user = new UserEntity("firstName", "lastName", "email" + new Date().getTime(), "1L", "postCode",
                "address", new Date().getTime(),"login" + new Date().getTime(), "password", null, null);

        RoleEntity role = new RoleEntity(user.getLogin(), RoleTypes.ROLE_USER);
        user.setRole(role);
        role.setUser(user);

        OrderEntity orderEntity1 = new OrderEntity(new Date().getTime(), OrderStatus.PREPARING, user);

        List<OrderEntity> orderList = new ArrayList<>();
        orderList.add(orderEntity1);

        user.setOrders(orderList);

        Session session = sessionFactory.openSession();
        Long customerID = (Long) session.save(user);
        Long orderID1 = (Long) session.save(orderEntity1);
        session.close();
        System.out.println(customerID);
        System.out.println(orderID1);


        /*
            Phase 2
         */
        Session session1 = sessionFactory.openSession();
        UserEntity dbUser = (UserEntity) session1.load(UserEntity.class, customerID);
        List<OrderEntity> orders = dbUser.getOrders();

        OrderEntity order1 = orders.get(0);
        System.out.println(order1.getProductIDList());
        List<Long> products = order1.getProductIDList();
        products.add(123L);
        products.add(34235L);
        products.add(234426546L);

        order1.setProductIDList(products);
        System.out.println(order1.getId());
        session1.save(order1);
        OrderEntity dbOrder = (OrderEntity) session1.get(OrderEntity.class, orderID1);
        System.out.println(dbOrder.getProductIDList());

        session1.close();

        /*
            Phase 3
         */

        Session session2 = sessionFactory.openSession();

        OrderEntity dbOrder1 = (OrderEntity) session2.get(OrderEntity.class, orderID1);
        System.out.println(dbOrder1.getProductIDList());

        session2.close();
    }

    @Test
    @Transactional
    public void AddProductsIaAOrderTest() {
        Session session = sessionFactory.getCurrentSession();

        OrderEntity orderEntity = (OrderEntity) session.get(OrderEntity.class, 1L);

        List<Long> productIDList = orderEntity.getProductIDList();
        productIDList.add(1L);
        productIDList.add(1L);
        productIDList.add(1L);
        orderEntity.setProductIDList(productIDList);

        session.update(orderEntity);
        session.flush();

    }
}
