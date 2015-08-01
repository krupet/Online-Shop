package ua.com.krupet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ua.com.krupet.jsfbeans.*;

/**
 * Created by krupet on 7/22/15.
 */

@Configuration
@ComponentScan({"ua.com.krupet"})
public class JSFBeansConfig {

    @Bean
    public ProductBean productBean() {
        return new ProductBean();
    }

    @Bean
    public UserBean userBean() {
        return new UserBean();
    }

    @Bean
    public OrderBean orderBean() {
        return new OrderBean();
    }

    @Bean
    public UserInfoBean userInfoBean() {
        return new UserInfoBean();
    }

    @Bean
    public ShopBean shopBean() {
        return new ShopBean();
    }
}
