package ua.com.krupet;

import javax.faces.bean.ManagedBean;
import java.io.Serializable;

/**
 * Created by krupet on 11.07.2015.
 */
@ManagedBean
public class Role implements Serializable {

    private String id;
    private String username;
    private String roleType;

    public Role() {
    }

    public Role(String username, String roleType) {
        this.username = username;
        this.roleType = roleType;
    }

    public Role(String id, String username, String roleType) {
        this.id = id;
        this.username = username;
        this.roleType = roleType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String role) {
        this.roleType = role;
    }
}
