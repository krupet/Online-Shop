package ua.com.krupet.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.krupet.Order;
import ua.com.krupet.OrderStatus;
import ua.com.krupet.dao.OrdersDAO;
import ua.com.krupet.entity.OrderEntity;
import ua.com.krupet.entity.UserEntity;

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
        /*
            to avoid problems I set user as null
         */
        return new Order(orderEntity.getId().toString(), orderEntity.getCreationDate().toString(),
                orderEntity.getStatus().toString(), null, orderEntity.getProductIDList());
    }

    @Override
    public Order updateOrder(Order order) {

        Long orderID = Long.parseLong(order.getId());
        Session session = sessionFactory.getCurrentSession();

        OrderEntity orderEntity = (OrderEntity) session.get(OrderEntity.class, orderID);

        if (orderEntity == null) throw new RuntimeException("bad request - there is no order with id ("
                                                                + orderID + ") in database");

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

        return null;
    }

    @Override
    public List<Order> getOrdersListByUserID(Long userID) {
        return null;
    }

    @Override
    public Order deleteOrder(Order order) {
        return null;
    }
}
