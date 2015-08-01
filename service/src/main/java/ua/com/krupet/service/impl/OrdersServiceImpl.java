package ua.com.krupet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.com.krupet.Order;
import ua.com.krupet.dao.OrdersDAO;
import ua.com.krupet.service.OrdersService;

import java.util.List;

/**
 * Created by krupet on 7/27/15.
 */
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDAO ordersDAO;

    @Override
    @Transactional
    public Order createOrder(Long userID, Order order) {
        return ordersDAO.createOrder(userID, order);
    }

    @Override
    @Transactional
    public Order getOrderByID(Long orderID) {
        return ordersDAO.getOrderByID(orderID);
    }

    @Override
    @Transactional
    public Order updateOrderStatus(Order order) {
        return ordersDAO.updateOrderStatus(order);
    }

    @Override
    @Transactional
    public List<Order> getOrdersList() {
        return ordersDAO.getOrdersList();
    }

    @Override
    @Transactional
    public List<Order> getOrdersListByUserID(Long userID) {
        return ordersDAO.getOrdersListByUserID(userID);
    }

    @Override
    @Transactional
    public Order deleteOrder(Order order) {
        return ordersDAO.deleteOrder(order);
    }
}
