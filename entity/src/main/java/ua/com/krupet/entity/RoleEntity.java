package ua.com.krupet.entity;

import org.hibernate.annotations.GenericGenerator;
import ua.com.krupet.RoleTypes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * EntityClass representing some role, used for Spring Security
 *
 * @author krupet
 * @see ua.com.krupet.entity.UserEntity
 */
@Entity
@Table(name = "USER_ROLES")
public class RoleEntity implements Serializable{

    @Id
    @Column(name = "ROLE_ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name="gen", strategy="foreign",
            parameters={@org.hibernate.annotations.Parameter(name="property", value="user")})
    private Long id;

    @Column(name = "USER_NAME")
    private String username;

    @Column(name = "USER_ROLE")
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
