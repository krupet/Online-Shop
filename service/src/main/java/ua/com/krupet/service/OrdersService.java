package ua.com.krupet.service;

import ua.com.krupet.Order;

import java.util.List;

/**
 * Created by krupet on 7/21/15.
 */
public interface OrdersService {

    Order createOrder(Long userID, Order order);
    Order getOrderByID(Long orderID);
    Order updateOrder(Order order);
    List<Order> getOrdersList();
    List<Order> getOrdersListByUserID(Long userID);
    Order deleteOrder(Order order);
}
