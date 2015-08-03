package ua.com.krupet.jsfbeans;

import org.springframework.beans.factory.annotation.Autowired;
import ua.com.krupet.service.OrdersService;
import ua.com.krupet.service.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

/**
 * Created by krupet on 8/3/15.
 * TODO: provide proper naming in a project
 */

@ManagedBean
@SessionScoped
public class UsrInfBean implements Serializable{

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private UserService userService;
}
