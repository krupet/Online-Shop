package ua.com.krupet.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.krupet.Order;
import ua.com.krupet.Role;
import ua.com.krupet.RoleTypes;
import ua.com.krupet.User;
import ua.com.krupet.dao.UsersDAO;
import ua.com.krupet.entity.OrderEntity;
import ua.com.krupet.entity.RoleEntity;
import ua.com.krupet.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by krupet on 11.07.2015.
 */
public class UsersDAOImpl implements UsersDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User createUser(User user) {

        Session session = sessionFactory.getCurrentSession();

        UserEntity customerEntity = new UserEntity(user.getFirstName(), user.getLastName(), user.getEmail(), user.getAge(),
                user.getPostCode(), user.getAddress(), Long.parseLong(user.getCreationDate()), user.getLogin(), user.getPassword(),
                null, null);

        RoleEntity role = new RoleEntity(user.getLogin(), RoleTypes.ROLE_USER); // TODO: role hardcode!
        customerEntity.setRole(role);

        role.setUser(customerEntity);

        Long customerID = (Long) session.save(customerEntity);
        UserEntity dbCustomer = (UserEntity) session.get(UserEntity.class, customerID);

        if (dbCustomer != null) return new User(dbCustomer.getId().toString());
        else return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUsersList() {

        Session session = sessionFactory.getCurrentSession();

        List<UserEntity> userEntities = (List<UserEntity>) session.createCriteria(UserEntity.class).list();

        /*
            if there is no records in DB returning empty list
         */
        if (userEntities == null) return new ArrayList<>();

        List<User> users = new ArrayList<>();
        List<OrderEntity> orderEntities = null;
        List<Order> orders = null;
        User user = null;
        Order order = null;

        for (UserEntity userEntity : userEntities) {
            user = new User(userEntity.getId().toString(), userEntity.getFirstName(),userEntity.getLastName(),
                    userEntity.getEmail(), userEntity.getAge(), userEntity.getPostCode(), userEntity.getAddress(),
                    userEntity.getCreationDate().toString(), userEntity.getLogin(), userEntity.getPassword(), null, null);

            user.setRole(new Role(userEntity.getRole().getId(),
                                  userEntity.getRole().getUsername(),
                                  userEntity.getRole().getRoleType().toString()));

            orderEntities = userEntity.getOrders();
            orders = new ArrayList<>();
            if (orderEntities != null) {
                for (OrderEntity orderEntity : orderEntities) {
                    /*
                        to avoid recursive call (orderEntity.getUser()) setting user (customer) as null
                     */
                    order = new Order(orderEntity.getId().toString(), orderEntity.getCreationDate().toString(),
                                      orderEntity.getStatus().toString(), null, orderEntity.getProductIDList());
                    orders.add(order);
                }
            }

            user.setOrders(orders);

            users.add(user);
        }

        return users;
    }
}
