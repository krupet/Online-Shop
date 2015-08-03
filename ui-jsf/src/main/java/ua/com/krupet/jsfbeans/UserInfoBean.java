package ua.com.krupet.jsfbeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ua.com.krupet.Order;
import ua.com.krupet.User;
import ua.com.krupet.service.OrdersService;
import ua.com.krupet.service.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by krupet on 7/31/15.
 */
@ManagedBean
@RequestScoped
public class UserInfoBean implements Serializable {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private UserService userService;

    private User user = new User();
    private List<Order> ordersList;
    private String userName = "";

    public User getUser() {
        if (!"".equals(userName)) user = userService.getUserByUserName(userName);
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Order> getOrdersList() {

        String userID = user.getId();
        if (userID != null) {
            return ordersService.getOrdersListByUserID(Long.parseLong(userID));
        } else return new ArrayList<>();
    }

    public void setOrdersList(List<Order> ordersList) {
        this.ordersList = ordersList;
    }

    public String getUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        userName = auth.getName();
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
