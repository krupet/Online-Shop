package ua.com.krupet;

import java.util.List;

/**
 * Created by krupet on 11.07.2015.
 */
public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private String email; // TODO: md5 hashing!
    private String age;
    private String postCode;
    private String address;
    private Long creationDate;

    private String login;
    private String password; // TODO: md5 hashing!

    private Role role;

    private List<Order> orders;

    public User(Long id) {
        this.id = id;
    }

    public User(String firstName, String lastName, String email, String age, String postCode, String address,
                Long creationDate, String login, String password, Role role, List<Order> orders) {
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

    public User(Long id, String firstName, String lastName, String email, String age, String postCode, String address,
                Long creationDate, String login, String password, Role role, List<Order> orders) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (!firstName.equals(user.firstName)) return false;
        if (!lastName.equals(user.lastName)) return false;
        if (!email.equals(user.email)) return false;
        if (age != null ? !age.equals(user.age) : user.age != null) return false;
        if (!postCode.equals(user.postCode)) return false;
        if (!address.equals(user.address)) return false;
        if (!creationDate.equals(user.creationDate)) return false;
        if (!login.equals(user.login)) return false;
        if (!password.equals(user.password)) return false;
        if (role != null ? !role.equals(user.role) : user.role != null) return false;
        return !(orders != null ? !orders.equals(user.orders) : user.orders != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + postCode.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + creationDate.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (orders != null ? orders.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
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
