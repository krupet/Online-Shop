package ua.com.krupet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ua.com.krupet.service.ProductService;
import ua.com.krupet.service.impl.ProductServiceImpl;

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
}