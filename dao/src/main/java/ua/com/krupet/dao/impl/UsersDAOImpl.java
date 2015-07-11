package ua.com.krupet.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.krupet.RoleTypes;
import ua.com.krupet.User;
import ua.com.krupet.dao.UsersDAO;
import ua.com.krupet.entity.RoleEntity;
import ua.com.krupet.entity.UserEntity;

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
                user.getPostCode(), user.getAddress(), user.getCreationDate(), user.getLogin(), user.getPassword(),
                null, null);

        RoleEntity role = new RoleEntity(user.getLogin(), RoleTypes.ROLE_USER);
        customerEntity.setRole(role);

        Long customerID = (Long) session.save(customerEntity);
        UserEntity dbCustomer = (UserEntity) session.get(UserEntity.class, customerID);

        if (dbCustomer != null) return new User(dbCustomer.getId());
        else return null;
    }
}
