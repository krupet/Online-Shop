package ua.com.krupet.jsfbeans;

import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.krupet.Role;
import ua.com.krupet.RoleTypes;
import ua.com.krupet.User;
import ua.com.krupet.jsfbeans.util.LazyUserDataModel;
import ua.com.krupet.service.UserService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by krupet on 7/26/15.
 */

@ManagedBean
@SessionScoped
public class UserBean implements Serializable{

    @Autowired
    private UserService userService;

    private LazyDataModel<User> lazyDataModel;
    private User selectedUser;
    private User newUser = new User();
    private String userRole = "";

    @PostConstruct
    public void init() {
        lazyDataModel = new LazyUserDataModel(userService);
    }

    public LazyDataModel<User> getLazyDataModel() {
        return lazyDataModel;
    }

    public void addNewUser(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean success = false;

        newUser.setCreationDate("" + new Date().getTime());
        newUser.setRole(new Role(newUser.getLogin(), userRole));
        if (userService.createUser(newUser) != null) {
            success = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Created!", "new user created successfully!");
        } else message = new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Error", "error during creating new user");

        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("success", success);
    }

    public void editUser(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean success = false;

        Long userID = Long.parseLong(selectedUser.getId());

        selectedUser.getRole().setRoleType(userRole);
        if (userService.updateUser(selectedUser) != null) {
            success = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Updated!", "user with ID("+ userID + ") updated successfully!");
        } else message = new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Error", "error during updating user with ID(" + userID + ")!");

        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("success", success);
    }

    public User getNewUser() {
        return newUser;
    }

    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
