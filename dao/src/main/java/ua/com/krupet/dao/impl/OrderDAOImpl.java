package ua.com.krupet.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.krupet.Order;
import ua.com.krupet.OrderStatus;
import ua.com.krupet.Product;
import ua.com.krupet.dao.OrdersDAO;
import ua.com.krupet.entity.OrderEntity;
import ua.com.krupet.entity.ProductEntity;
import ua.com.krupet.entity.UserEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Basic implementation of OrdersDAO interface, for more info see ua.com.krupet.dao.OrdersDAO
 *
 * Such complexity is explained by using DTO objects to avoid lazy loading/initialization problems
 */
public class OrderDAOImpl implements OrdersDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Order createOrder(Long userID, Order order) {

        Session session = sessionFactory.getCurrentSession();

        UserEntity userEntity = (UserEntity) session.get(UserEntity.class, userID);

        if (userEntity == null)
            throw new RuntimeException("bad request - no user with id (" + userID + ") in database");

        List<Product> products = order.getProductIDList();
        if (products == null)
            throw new RuntimeException("error - you can't create order without products (products == null)");
        List<ProductEntity> productEntities = new ArrayList<>();
        Long productID = null;

        for (Product product : products) {
            productID = Long.parseLong(product.getId());
            productEntities.add((ProductEntity) session.get(ProductEntity.class, productID));
        }

        List<OrderEntity> orderEntities = userEntity.getOrders();
        /*
            hot fix
         */
        if (orderEntities == null) orderEntities = new ArrayList<>();
        Long creationDate = new Date().getTime();
        OrderEntity orderEntity = new OrderEntity(creationDate, OrderStatus.ACCEPTED, userEntity, productEntities);
        orderEntities.add(orderEntity);

        userEntity.setOrders(orderEntities);

        session.update(userEntity);
        Long orderID = (Long) session.save(orderEntity);

        session.flush();

        OrderEntity dbOrderEntity = (OrderEntity) session.get(OrderEntity.class, orderID);

        if (dbOrderEntity != null) return new Order(dbOrderEntity.getId().toString());
        else return null;
    }

    @Override
    public Order createOrderByUsersName(String userName, Order order) {

        Session session = sessionFactory.getCurrentSession();

        UserEntity userEntity = (UserEntity) session.createCriteria(UserEntity.class)
                                                    .add(Restrictions.eq("login", userName))
                                                    .uniqueResult();

        if (userEntity == null)
            throw new RuntimeException("bad request - no user with username (" + userName + ") in database");

        List<Product> products = order.getProductIDList();
        if (products == null)
            throw new RuntimeException("error - you can't create order without products (products == null)");
        List<ProductEntity> productEntities = new ArrayList<>();
        Long productID = null;

        for (Product product : products) {
            productID = Long.parseLong(product.getId());
            productEntities.add((ProductEntity) session.get(ProductEntity.class, productID));
        }

        List<OrderEntity> orderEntities = userEntity.getOrders();
        /*
            hot fix
         */
        if (orderEntities == null) orderEntities = new ArrayList<>();
        Long creationDate = new Date().getTime();
        OrderEntity orderEntity = new OrderEntity(creationDate, OrderStatus.ACCEPTED, userEntity, productEntities);
        orderEntities.add(orderEntity);

        userEntity.setOrders(orderEntities);

        session.update(userEntity);
        Long orderID = (Long) session.save(orderEntity);

        session.flush();

        OrderEntity dbOrderEntity = (OrderEntity) session.get(OrderEntity.class, orderID);

        if (dbOrderEntity != null) return new Order(dbOrderEntity.getId().toString());
        else return null;
    }

    @Override
    public Order getOrderByID(Long orderID) {

        Session session = sessionFactory.getCurrentSession();

        OrderEntity orderEntity = (OrderEntity) session.get(OrderEntity.class, orderID);

        if (orderEntity == null) throw new RuntimeException("bad request - there is no order with id ("
                                                                + orderID + ") in database");

        List<ProductEntity> productEntityList = orderEntity.getProductIDList();
        List<Product> productList = new ArrayList<>();

        /*
            hotfix NPE
         */
        if (productEntityList != null) {
            productList.addAll(productEntityList.stream()
                    .map(productEntity -> new Product(
                            productEntity.getId().toString(), productEntity.getName(), productEntity.getBrand(),
                            productEntity.getDescription(), productEntity.getPrice().toPlainString(),
                            productEntity.getPictureLink(), productEntity.getCreationDate().toString()))
                    .collect(Collectors.toList()));
        }


        return new Order(orderEntity.getId().toString(), orderEntity.getCreationDate().toString(),
                orderEntity.getStatus().toString(), orderEntity.getCustomer().getId().toString(), productList);
    }

    @Override
    public Order updateOrderStatus(Order order) {

        Long orderID = Long.parseLong(order.getId());
        Session session = sessionFactory.getCurrentSession();

        OrderEntity orderEntity = (OrderEntity) session.get(OrderEntity.class, orderID);

        if (orderEntity == null) throw new RuntimeException("bad request - there is no order with id ("
                                                                + orderID + ") in database");

        orderEntity.setStatus(OrderStatus.valueOf(order.getOrderStatus()));

        session.update(orderEntity);
        session.flush();

        /*
            TODO: create some better update check
         */
        OrderEntity updatedOrder = (OrderEntity) session.get(OrderEntity.class, orderID);
        if (updatedOrder == null) throw new RuntimeException("internal error during updating order with id (" + orderID + ")!");
        else return new Order(updatedOrder.getId().toString());
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Order> getOrdersList() {

        Session session = sessionFactory.getCurrentSession();
        List<OrderEntity> orderEntities = (List<OrderEntity>) session.createCriteria(OrderEntity.class).list();

        List<Order> orders = new ArrayList<>();

        List<Product> productList = null;
        List<ProductEntity> productEntityList = null;

        for (OrderEntity orderEntity : orderEntities) {

            productEntityList = orderEntity.getProductIDList();
            productList = new ArrayList<>();

            /*
                NPE fix
             */
            if (productEntityList != null) {
                productList.addAll(productEntityList.stream().map(productEntity -> new Product(productEntity.getId().toString(),
                        productEntity.getName(), productEntity.getBrand(), productEntity.getDescription(),
                        productEntity.getPrice().toPlainString(), productEntity.getPictureLink(),
                        productEntity.getCreationDate().toString())).collect(Collectors.toList()));
            }

            orders.add(new Order(orderEntity.getId().toString(), orderEntity.getCreationDate().toString(),
                    orderEntity.getStatus().toString(), orderEntity.getCustomer().getId().toString(),
                    productList));
        }
        return orders;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Order> getOrdersListByUserID(Long userID) {

        Session session = sessionFactory.getCurrentSession();

        UserEntity userEntity = (UserEntity) session.get(UserEntity.class, userID);
        if (userEntity == null) throw new RuntimeException("bad request - there is no user with id ("
                                                                + userID + ") in database");

        Criteria criteria = session.createCriteria(OrderEntity.class);
        criteria.createAlias("customer", "c");
        criteria.add(Restrictions.eq("c.id", userID));
        List<OrderEntity> orderEntities = (List<OrderEntity>) criteria.list();

        Order order = null;
        List<Order> orders = new ArrayList<>();

        List<Product> productList = null;
        List<ProductEntity> productEntityList = null;

        for (OrderEntity orderEntity : orderEntities) {

            productEntityList = orderEntity.getProductIDList();
            productList = new ArrayList<>();

            if (productEntityList != null) {
                productList.addAll(productEntityList.stream().map(productEntity -> new Product(productEntity.getId().toString(),
                        productEntity.getName(), productEntity.getBrand(), productEntity.getDescription(),
                        productEntity.getPrice().toPlainString(), productEntity.getPictureLink(),
                        productEntity.getCreationDate().toString())).collect(Collectors.toList()));
            }

            orders.add(new Order(orderEntity.getId().toString(), orderEntity.getCreationDate().toString(),
                    orderEntity.getStatus().toString(), orderEntity.getCustomer().getId().toString(),
                    productList));
        }

        return orders;
    }

    @Override
    public Order deleteOrder(Order order) {

        Long orderID = Long.parseLong(order.getId());

        Session session = sessionFactory.getCurrentSession();
        OrderEntity orderEntity = (OrderEntity) session.get(OrderEntity.class, orderID);
        if (orderEntity == null) throw new RuntimeException("bad request - there is no order with id ("
                                                                    + orderID + ") in database");

        session.delete(orderEntity);
        session.flush();

        OrderEntity removedOrderEntity = (OrderEntity) session.get(OrderEntity.class, orderID);
        if (removedOrderEntity != null) throw new RuntimeException("internal error during deletion order with id ("
                                                        + orderID + ") in database");

        return new Order();
    }
}
