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
 * Maybe, to avoid redundant queries against db I should return users without their orders first
 * and return orders by user ID when it is really needed?
 * Created by krupet on 11.07.2015.
 */
public class UsersDAOImpl implements UsersDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User createUser(User user) {

        Session session = sessionFactory.getCurrentSession();

        UserEntity customerEntity = new UserEntity(user.getFirstName(), user.getLastName(), user.getEmail(),
                user.getAge(), user.getPostCode(), user.getAddress(), Long.parseLong(user.getCreationDate()),
                user.getLogin(), user.getPassword(), null, null);

        RoleEntity role = new RoleEntity(user.getLogin(), RoleTypes.valueOf(user.getRole().getRoleType()));
        customerEntity.setRole(role);

        role.setUser(customerEntity);

        Long customerID = (Long) session.save(customerEntity);
        UserEntity dbCustomer = (UserEntity) session.get(UserEntity.class, customerID);

        if (dbCustomer != null) return new User(dbCustomer.getId().toString());
        else return null;
    }

    @Override
    public User updateUser(User user) {

        Long userID = Long.parseLong(user.getId());
        Session session = sessionFactory.getCurrentSession();

        UserEntity userEntity = (UserEntity) session.get(UserEntity.class, userID);
        if (userEntity == null) throw new RuntimeException("bad request - there is no user with id("
                                                                + userID + ") in database");

        RoleEntity newRole = userEntity.getRole();
        newRole.setRoleType(RoleTypes.valueOf(user.getRole().getRoleType()));

        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmail(user.getEmail());
        userEntity.setAge(user.getAge());
        userEntity.setPostCode(user.getPostCode());
        userEntity.setAddress(user.getAddress());
        userEntity.setPassword(user.getPassword());
        userEntity.setRole(newRole);


        session.update(newRole);
        session.update(userEntity);
        session.flush();

        /*
            TODO: some proper checks
         */
        UserEntity updatedUser = (UserEntity) session.get(UserEntity.class, userID);

        RoleEntity roleEnt = updatedUser.getRole();
        Role role = new Role(roleEnt.getId().toString(), roleEnt.getUsername(), roleEnt.getRoleType().toString());

        return new User(updatedUser.getId().toString(), updatedUser.getFirstName(), updatedUser.getLastName(),
                updatedUser.getEmail(), updatedUser.getAge(), updatedUser.getPostCode(), updatedUser.getAddress(),
                updatedUser.getCreationDate().toString(), updatedUser.getLogin(), updatedUser.getPassword(), role, null);
    }

    @Override
    public User getUserByID(Long userID) {

        Session session = sessionFactory.getCurrentSession();
        UserEntity userEntity = (UserEntity) session.get(UserEntity.class, userID);
        if (userEntity == null) throw new RuntimeException("bad request - there is no user with id("
                                                                + userID + ") in database");
        RoleEntity roleEnt = userEntity.getRole();
        Role role = new Role(roleEnt.getId().toString(), roleEnt.getUsername(), roleEnt.getRoleType().toString());

        /*
            setting orders as null to avoid redundant queries
         */
        return new User(userEntity.getId().toString(), userEntity.getFirstName(), userEntity.getLastName(),
                userEntity.getEmail(), userEntity.getAge(), userEntity.getPostCode(), userEntity.getAddress(),
                userEntity.getCreationDate().toString(), userEntity.getLogin(), userEntity.getPassword(), role, null);
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
        User user = null;
//        List<OrderEntity> orderEntities = null;
//        List<Order> orders = null;
//        Order order = null;

        /*
            to avoid redundant queries I will return users list without their orders list
         */
        for (UserEntity userEntity : userEntities) {
            user = new User(userEntity.getId().toString(), userEntity.getFirstName(),userEntity.getLastName(),
                    userEntity.getEmail(), userEntity.getAge(), userEntity.getPostCode(), userEntity.getAddress(),
                    userEntity.getCreationDate().toString(), userEntity.getLogin(), userEntity.getPassword(), null, null);

            user.setRole(new Role(userEntity.getRole().getId().toString(),
                                  userEntity.getRole().getUsername(),
                                  userEntity.getRole().getRoleType().toString()));

//            orderEntities = userEntity.getOrders();
//            orders = new ArrayList<>();
//            if (orderEntities != null) {
//                for (OrderEntity orderEntity : orderEntities) {
//
//                    order = new Order(orderEntity.getId().toString(), orderEntity.getCreationDate().toString(),
//                                      orderEntity.getStatus().toString(), orderEntity.getCustomer().getId().toString(),
//                                      orderEntity.getProductIDList());
//                    orders.add(order);
//                }
//            }
//            user.setOrders(orders);
            user.setOrders(null);

            users.add(user);
        }

        return users;
    }
}
