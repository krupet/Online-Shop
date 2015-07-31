package ua.com.krupet.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.krupet.Order;
import ua.com.krupet.OrderStatus;
import ua.com.krupet.dao.OrdersDAO;
import ua.com.krupet.entity.OrderEntity;
import ua.com.krupet.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by krupet on 7/27/15.
 */
public class OrderDAOImpl implements OrdersDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Order createOrder(Long userID, Order order) {

        Session session = sessionFactory.getCurrentSession();

        UserEntity userEntity = (UserEntity) session.get(UserEntity.class, userID);

        if (userEntity == null) throw new RuntimeException("bad request - no user with id (" + userID + ") in database");

        List<OrderEntity> orderEntities = userEntity.getOrders();
        OrderEntity orderEntity = new OrderEntity(Long.parseLong(order.getCreationDate()),
                OrderStatus.valueOf(order.getOrderStatus()), userEntity, order.getProductIDList());
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

        return new Order(orderEntity.getId().toString(), orderEntity.getCreationDate().toString(),
                orderEntity.getStatus().toString(), orderEntity.getCustomer().getId().toString(), orderEntity.getProductIDList());
    }

    @Override
    public Order updateOrder(Order order) {

        Long orderID = Long.parseLong(order.getId());
        Session session = sessionFactory.getCurrentSession();

        OrderEntity orderEntity = (OrderEntity) session.get(OrderEntity.class, orderID);

        if (orderEntity == null) throw new RuntimeException("bad request - there is no order with id ("
                                                                + orderID + ") in database");

        /*
            in order only status and product list is allowed to change
         */
        orderEntity.setStatus(OrderStatus.valueOf(order.getOrderStatus()));
        orderEntity.setProductIDList(order.getProductIDList());

        session.update(orderEntity);
        session.flush();

        return order;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Order> getOrdersList() {

        Session session = sessionFactory.getCurrentSession();
        List<OrderEntity> orderEntities = (List<OrderEntity>) session.createCriteria(OrderEntity.class).list();

        System.out.println("DAO ENT SIZE : " + orderEntities.size());
        Order order = null;
        List<Order> orders = new ArrayList<>();
        for (OrderEntity orderEntity : orderEntities) {
            order = new Order(orderEntity.getId().toString(), orderEntity.getCreationDate().toString(),
                              orderEntity.getStatus().toString(), orderEntity.getCustomer().getId().toString(),
                              orderEntity.getProductIDList());
            orders.add(order);
        }
        System.out.println("\tDAO SIZE : " + orders.size());
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
        for (OrderEntity orderEntity : orderEntities) {
            order = new Order(orderEntity.getId().toString(), orderEntity.getCreationDate().toString(),
                              orderEntity.getStatus().toString(), orderEntity.getCustomer().getId().toString(),
                              orderEntity.getProductIDList());
            orders.add(order);
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
