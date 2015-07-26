package ua.com.krupet;

import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.List;

/**
 * Created by krupet on 11.07.2015.
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
    private String password; // TODO: md5 hashing!

    private Role role;

    private List<Order> orders;

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
}
