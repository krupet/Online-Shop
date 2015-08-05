package ua.com.krupet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ua.com.krupet.jsfbeans.IndexBean;
import ua.com.krupet.jsfbeans.LoginBean;
import ua.com.krupet.jsfbeans.OrderBean;
import ua.com.krupet.jsfbeans.ProductBean;
import ua.com.krupet.jsfbeans.ShopBean;
import ua.com.krupet.jsfbeans.UserBean;
import ua.com.krupet.jsfbeans.UserInfoBean;

/**
 * Spring config class for JSF beans
 *
 * @author krupet
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

    @Bean
    public IndexBean indexBean() {
        return new IndexBean();
    }

    @Bean
    public LoginBean loginBean() {
        return new LoginBean();
    }
}
