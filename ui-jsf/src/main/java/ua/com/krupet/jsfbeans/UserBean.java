package ua.com.krupet.jsfbeans;

import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.krupet.User;
import ua.com.krupet.jsfbeans.util.LazyUserDataModel;
import ua.com.krupet.service.UserService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by krupet on 7/26/15.
 */

@ManagedBean
@SessionScoped
public class UserBean {

    @Autowired
    private UserService userService;

    private LazyDataModel<User> lazyDataModel;
    private User user;

    @PostConstruct
    public void init() {
        lazyDataModel = new LazyUserDataModel(userService);
    }

    public LazyDataModel<User> getLazyDataModel() {
        return lazyDataModel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
