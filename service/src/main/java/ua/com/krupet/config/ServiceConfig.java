package ua.com.krupet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ua.com.krupet.service.OrdersService;
import ua.com.krupet.service.ProductService;
import ua.com.krupet.service.UserService;
import ua.com.krupet.service.impl.OrdersServiceImpl;
import ua.com.krupet.service.impl.ProductServiceImpl;
import ua.com.krupet.service.impl.UserServiceImpl;

/**
 * Created by krupet on 7/21/15.
 */
@Configuration
@ComponentScan({"ua.com.krupet"})
public class ServiceConfig {

    @Bean
    public ProductService productService() {
        return new ProductServiceImpl();
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean
    public OrdersService ordersService() {
        return new OrdersServiceImpl();
    }
}
