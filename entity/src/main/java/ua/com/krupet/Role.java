package ua.com.krupet;

import javax.faces.bean.ManagedBean;
import java.io.Serializable;

/**
 * DTO representing users role in the system
 *
 * @author krupet
 * @see ua.com.krupet.RoleTypes
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (id != null ? !id.equals(role.id) : role.id != null) return false;
        if (username != null ? !username.equals(role.username) : role.username != null) return false;
        return !(roleType != null ? !roleType.equals(role.roleType) : role.roleType != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (roleType != null ? roleType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", roleType='" + roleType + '\'' +
                '}';
    }
}
