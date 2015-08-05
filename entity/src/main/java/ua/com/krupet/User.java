package ua.com.krupet;

import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.List;

/**
 * DTO representing some user of the shop
 *
 * @author krupet
 * @see ua.com.krupet.Role
 * @see ua.com.krupet.Order
 */
@ManagedBean
public class User implements Serializable{

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String age;
    private String postCode;
    private String address;
    private String creationDate;

    private String login;
    private String password;

    private Role role;

    private List<Order> orders;

    public User() {
    }

    public User(String id) {
        this.id = id;
    }

    public User(String firstName, String lastName, String email, String age, String postCode, String address,
                String creationDate, String login, String password, Role role, List<Order> orders) {
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

    public User(String id, String firstName, String lastName, String email, String age, String postCode, String address,
                String creationDate, String login, String password, Role role, List<Order> orders) {
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (age != null ? !age.equals(user.age) : user.age != null) return false;
        if (postCode != null ? !postCode.equals(user.postCode) : user.postCode != null) return false;
        if (address != null ? !address.equals(user.address) : user.address != null) return false;
        if (creationDate != null ? !creationDate.equals(user.creationDate) : user.creationDate != null) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        return !(role != null ? !role.equals(user.role) : user.role != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (postCode != null ? postCode.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age='" + age + '\'' +
                ", postCode='" + postCode + '\'' +
                ", address='" + address + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", orders=" + orders +
                '}';
    }
}
