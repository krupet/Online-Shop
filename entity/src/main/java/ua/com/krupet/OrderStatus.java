package ua.com.krupet;

/**
 * Statuses witch could have some order. Enum used to represent stage of some order.
 *
 * @author krupet
 * @see ua.com.krupet.Order
 */
public enum OrderStatus {
    ACCEPTED, PREPARING, READY_TO_SEND, SENT, DELIVERED, CANCELED
}
