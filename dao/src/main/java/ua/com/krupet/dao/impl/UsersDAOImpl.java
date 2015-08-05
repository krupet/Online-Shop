package ua.com.krupet.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.krupet.Role;
import ua.com.krupet.RoleTypes;
import ua.com.krupet.User;
import ua.com.krupet.dao.UsersDAO;
import ua.com.krupet.entity.RoleEntity;
import ua.com.krupet.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Basic implementation for UsersDAO interface
 *
 * To avoid redundant queries against db I will return users without their orders
 * I am returning orders by user ID when it is really needed in OrdersDAO
 *
 * Such complexity is explained by using DTO objects to avoid lazy loading/initialization problems
 *
 * @author krupet
 * @see ua.com.krupet.dao.UsersDAO
 * @see ua.com.krupet.User
 * @see ua.com.krupet.Role
 * @see ua.com.krupet.RoleTypes
 */
public class UsersDAOImpl implements UsersDAO {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Creates new user in DB
     *
     * @param user - DTO representing new user to persist
     * @return DTO representing persisted user
     */
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

    /**
     * Updates users information
     *
     * @param user - DTO representing persisted user to update
     * @return DTO representing updated user
     */
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

        UserEntity updatedUser = (UserEntity) session.get(UserEntity.class, userID);

        if (updatedUser != null) {
            RoleEntity roleEnt = updatedUser.getRole();
            Role role = new Role(roleEnt.getId().toString(), roleEnt.getUsername(), roleEnt.getRoleType().toString());

            return new User(updatedUser.getId().toString(), updatedUser.getFirstName(), updatedUser.getLastName(),
                    updatedUser.getEmail(), updatedUser.getAge(), updatedUser.getPostCode(), updatedUser.getAddress(),
                    updatedUser.getCreationDate().toString(), updatedUser.getLogin(), updatedUser.getPassword(), role,
                    null);

        } else return null;
    }

    /**
     * Retrieves user from DB by its ID
     *
     * @param userID - ID of required user
     * @return DTO representing required user
     */
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

    /**
     * Retrieves user by its username (login)
     * @param userName - username of required user
     * @return DTO representing required user
     */
    @Override
    public User getUserByUserName(String userName) {

        Session session = sessionFactory.getCurrentSession();
        UserEntity userEntity = (UserEntity) session.createCriteria(UserEntity.class)
                                                    .add(Restrictions.eq("login", userName))
                                                    .uniqueResult();

        if (userEntity == null) throw new RuntimeException("bad request - there is no user with username ("
                + userName + ") in database");
        RoleEntity roleEnt = userEntity.getRole();
        Role role = new Role(roleEnt.getId().toString(), roleEnt.getUsername(), roleEnt.getRoleType().toString());

        /*
            setting orders as null to avoid redundant queries
         */
        return new User(userEntity.getId().toString(), userEntity.getFirstName(), userEntity.getLastName(),
                userEntity.getEmail(), userEntity.getAge(), userEntity.getPostCode(), userEntity.getAddress(),
                userEntity.getCreationDate().toString(), userEntity.getLogin(), userEntity.getPassword(), role, null);
    }

    /**
     * Retrieves list of all users in DB
     * @return list of users
     */
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
        RoleEntity roleEnt = null;

        /*
            to avoid redundant queries I will return users list without their orders list
         */
        for (UserEntity userEntity : userEntities) {
            user = new User(userEntity.getId().toString(), userEntity.getFirstName(),userEntity.getLastName(),
                    userEntity.getEmail(), userEntity.getAge(), userEntity.getPostCode(), userEntity.getAddress(),
                    userEntity.getCreationDate().toString(), userEntity.getLogin(), userEntity.getPassword(), null, null);

            roleEnt = userEntity.getRole();

            /*
                hotfix
                TODO: find where is a problem
                some entity's role gains NPE
             */
            if (roleEnt != null) {
                user.setRole(new Role(roleEnt.getId().toString(),
                        roleEnt.getUsername(),
                        roleEnt.getRoleType().toString()));
            } else {
                user.setRole(new Role("", "", ""));
            }

            user.setOrders(null);

            users.add(user);
        }

        return users;
    }
}
