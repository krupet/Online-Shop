package ua.com.krupet;

/**
 * Created by krupet on 11.07.2015.
 */
public class Role {

    private String id;
    private String username;
    private String roleType;

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
