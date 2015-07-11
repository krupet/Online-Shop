package ua.com.krupet;

import java.math.BigDecimal;

/**
 * Created by krupet on 11.07.2015.
 */
public class Product {

    private Long id;
    private String name;
    private String brand;
    private String description;
    private BigDecimal price;
    private String pictureLink;
    private Long creationDate;

    private Order order;
}
