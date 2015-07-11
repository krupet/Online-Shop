package ua.com.krupet;

import java.util.List;

/**
 * Created by krupet on 11.07.2015.
 */
public class Order {

    private Long id;
    private Long creationDate;
    private OrderStatus status;
//    private String dispatchAddress;

    private User customer;
    private List<Product> productList;

}
