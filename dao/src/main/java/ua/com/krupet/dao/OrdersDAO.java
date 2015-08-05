package ua.com.krupet.dao;

import ua.com.krupet.Order;

import java.util.List;

/**
 * Defines interface for orders service that are able to retrieve orders information from DataBase (DB)
 * Note in service I am using DTO objects instead of entities to avoid some lazy initialization/loading
 * problems with JPA provider
 *
 * @author krupet
 * @see ua.com.krupet.Order
 * @see ua.com.krupet.OrderStatus
 */
public interface OrdersDAO {

    /**
     * Creates order in DB
     *
     * @param userID - user`s ID which belongs this order
     * @param order - DTO representing itself order
     * @return Order - DTO representing persisted order
     */
    Order createOrder(Long userID, Order order);

    /**
     * Creates order in DB
     *
     * @param userName - user`s name (login) which belongs this order
     * @param order - DTO representing itself order
     * @return Order - DTO representing persisted order
     */
    Order createOrderByUsersName(String userName, Order order);

    /**
     * Retrieves order from DB by its ID
     *
     * @param orderID - order`s ID
     * @return Order - DTO representing persisted order
     */
    Order getOrderByID(Long orderID);

    /**
     * Updates order status
     *
     * @param order - DTO representing persisted order which need to update
     * @return Order - DTO representing persisted order
     */
    Order updateOrderStatus(Order order);

    /**
     * Retrieves list of al orders in DB
     *
     * @return list of orders
     */
    List<Order> getOrdersList();

    /**
     * Retrieves list of orders of some user by its ID
     *
     * @param userID - user`s ID, which list of orders we need to get
     * @return list of orders with "belongs" to specified user
     */
    List<Order> getOrdersListByUserID(Long userID);

    /**
     * Removes order from DB
     *
     * @param order - DTO representing persisted order which need to be removed
     * @return "empty" DTO if removed successfully or null otherwise
     */
    Order deleteOrder(Order order);
}
