package ua.com.krupet.entity;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import ua.com.krupet.RoleTypes;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by krupet on 11.07.2015.
 */
@Entity
@Table(name = "user_roles")
public class RoleEntity implements Serializable{

    @Id
    @Column(name = "role_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name="gen", strategy="foreign", parameters={@org.hibernate.annotations.Parameter(name="property", value="user")})
    private Long id;

    @Column(name = "user_name")
    private String username;

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private RoleTypes roleType;

    @OneToOne
    @PrimaryKeyJoinColumn
    private UserEntity user;

    public RoleEntity() {
    }

    public RoleEntity(String username, RoleTypes roleType) {
        this.username = username;
        this.roleType = roleType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public RoleTypes getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleTypes role) {
        this.roleType = role;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
