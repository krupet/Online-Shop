package ua.com.krupet.dao;

import ua.com.krupet.Order;

import java.util.List;

/**
 * Created by krupet on 7/27/15.
 */
public interface OrdersDAO {

    Order createOrder(Long userID, Order order);
    Order getOrderByID(Long orderID);
    Order updateOrder(Order order);
    List<Order> getOrdersList();
    List<Order> getOrdersListByUserID(Long userID);
    Order deleteOrder(Order order);
}
