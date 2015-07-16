package ua.com.krupet.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.krupet.BaseDaoTest;
import ua.com.krupet.RoleTypes;
import ua.com.krupet.entity.RoleEntity;
import ua.com.krupet.entity.UserEntity;

import java.util.Date;

/**
 * Created by krupet on 04.07.2015.
 */
public class EntityDaoImplTest extends BaseDaoTest{

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void entityTest() {
        UserEntity user = new UserEntity("firstName", "lastName", "email", "1L", "postCode", "address",
                new Date().getTime(),"login", "password", null, null);

        RoleEntity role = new RoleEntity(user.getLogin(), RoleTypes.ROLE_USER);
        user.setRole(role);
        role.setUser(user);

        Session session = sessionFactory.openSession();
        Long customerID = (Long) session.save(user);
        session.close();
        System.out.println(customerID);
    }
}
