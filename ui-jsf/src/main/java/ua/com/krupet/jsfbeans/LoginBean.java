package ua.com.krupet.jsfbeans;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.krupet.Role;
import ua.com.krupet.RoleTypes;
import ua.com.krupet.User;
import ua.com.krupet.service.UserService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.Date;

/**
 * ManagedBean Class for login.xhtml page
 * Provides ability to register a new user with ROLE_USER restrictions
 *
 * @author krupet
 */
@ManagedBean
@RequestScoped
public class LoginBean  implements Serializable {

    @Autowired
    private UserService userService;

    private User user = new User();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Add new user in the "system" with ROLE_USER authorities
     *
     * @param event - primfaces stuff
     */
    public void addNewUser(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean success = false;

        user.setCreationDate("" + new Date().getTime());
        user.setRole(new Role(user.getLogin(), RoleTypes.ROLE_USER.toString()));
        if (userService.createUser(user) != null) {
            success = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Created!", "new user created successfully!");
        } else message = new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Error", "error during creating new user");

        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("success", success);
    }
}
