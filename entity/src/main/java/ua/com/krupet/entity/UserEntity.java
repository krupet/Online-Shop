package ua.com.krupet.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * EntityClass representing some user of shop
 *
 * @author krupet
 * @see ua.com.krupet.entity.RoleEntity
 */
@Entity
@Table(name = "USERS")
public class UserEntity implements Serializable{

    @Id
    @Column(name = "USER_ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_FIRST_NAME")
    private String firstName;

    @Column(name = "USER_LAST_NAME")
    private String lastName;

    @Column(name = "USER_EMAIL", unique = true)
    private String email;

    @Column(name = "USER_AGE", nullable = true)
    private String age;

    @Column(name = "USER_POST_CODE", nullable = false)
    private String postCode;

    @Column(name = "USER_ADDRESS", nullable = false)
    private String address;

    @Column(name = "USER_CREATION_DATE", nullable = false)
    private Long creationDate;

    @Column(name = "USER_LOGIN", nullable = false, unique = true)
    private String login;

    @Column(name = "USER_PASSWORD", nullable = false)
    private String password;

    @OneToOne(mappedBy="user")
    @Cascade(value=org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private RoleEntity role;

    @OneToMany(mappedBy = "customer")
    private List<OrderEntity> orders;

    public UserEntity() {
    }

    public UserEntity(String firstName, String lastName, String email, String age, String postCode, String address,
                      Long creationDate, String login, String password, RoleEntity role, List<OrderEntity> orders) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.postCode = postCode;
        this.address = address;
        this.creationDate = creationDate;
        this.login = login;
        this.password = password;
        this.role = role;
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (!firstName.equals(that.firstName)) return false;
        if (!lastName.equals(that.lastName)) return false;
        if (!email.equals(that.email)) return false;
        if (age != null ? !age.equals(that.age) : that.age != null) return false;
        if (!postCode.equals(that.postCode)) return false;
        if (!address.equals(that.address)) return false;
        if (!creationDate.equals(that.creationDate)) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;
        return !(orders != null ? !orders.equals(that.orders) : that.orders != null);

    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + postCode.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + creationDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age='" + age + '\'' +
                ", postCode='" + postCode + '\'' +
                ", address='" + address + '\'' +
                ", creationDate=" + creationDate +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", orders=" + orders +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }
}
