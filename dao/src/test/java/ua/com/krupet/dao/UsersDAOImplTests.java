package ua.com.krupet.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.com.krupet.BaseDaoTest;
import ua.com.krupet.Role;
import ua.com.krupet.RoleTypes;
import ua.com.krupet.User;
import ua.com.krupet.entity.RoleEntity;
import ua.com.krupet.entity.UserEntity;

import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

public class UsersDAOImplTests extends BaseDaoTest{

    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    @Transactional
    public void testPostNewUserAndExpectedIsOk() {
        User dbCustomer = usersDAO.createUser(getTestUser());
        assertNotNull(dbCustomer);
        assertNotNull(dbCustomer.getId());
    }

    @Test(expected = ConstraintViolationException.class)
    @Transactional
    public void testPostTwoUsersWithSameUsernameAndExpectedIsException() {
        User user = getTestUser();

        User dbCustomer1 = usersDAO.createUser(user);
        assertNotNull(dbCustomer1);
        assertNotNull(dbCustomer1.getId());

        User dbCustomer2 = usersDAO.createUser(user);
    }

    @Test
    @Transactional
    public void testUpdateUserAndExpectedIsOk() {

        UserEntity userEntity = getPersistedUserEntity();

        User user = new User(userEntity.getId().toString(), "upd_fName", "upd_lName", "upd_email", "upd_age",
                "upd_postCode","upd_address", Long.toString(new Date().getTime()), userEntity.getLogin(),
                "upd_password", new Role(userEntity.getLogin(), RoleTypes.ROLE_ADMIN.toString()), null);

        User updatedUser = usersDAO.updateUser(user);
        assertNotNull(updatedUser);
        assertNotNull(updatedUser.getId());
        assertNotNull(updatedUser.getLogin());
    }

    @Test
    @Transactional
    public void testGetUserByIDAndExpectedIsOk() {

        Long userID = getPersistedUserEntity().getId();

        User user = usersDAO.getUserByID(userID);
        assertNotNull(user);
        assertEquals((long) userID, Long.parseLong(user.getId()));
        assertNotNull(user.getId());
        assertNotNull(user.getLogin());
        assertNotNull(user.getRole());
    }

    @Test(expected = Exception.class)
    @Transactional
    public void testGetUserByNonExistingIDAndExpectedIsException() {

        Long userID = 1234567L;

        User user = usersDAO.getUserByID(userID);
        assertNull(user);
    }

    @Test
    @Transactional
    public void testGetUserByUsernameAndExpectedIsOk() {

        String userName = getPersistedUserEntity().getLogin();

        User user = usersDAO.getUserByUserName(userName);
        assertNotNull(user);
        assertEquals(userName, user.getLogin());
        assertNotNull(user.getId());
        assertNotNull(user.getLogin());
        assertNotNull(user.getRole());
    }

    @Test(expected = Exception.class)
    @Transactional
    public void testGetUserByNonExistingUsernameAndExpectedIsException() {

        String userName = "non_existing";

        User user = usersDAO.getUserByUserName(userName);
        assertNull(user);
    }

    @Test
    @Transactional
    public void testGetUsersListAndExpectedIsOk() {
        /*
            populating table users
         */
        getPersistedUserEntity();
        getPersistedUserEntity();
        getPersistedUserEntity();

        List<User> users = usersDAO.getUsersList();
        assertNotNull(users);
        System.out.println(users.size());
        assertEquals(true, users.size() > 2);

    }

    private User getTestUser() {
        /*
            using String.format to avoid collisions in some values
            where in db properties is unique = true
         */
        String userName = String.format("test_login%d", new Date().getTime());
        return new User("test_firstName",
                        "test_lastName",
                        String.format("test_email%d@gmail.com", new Date().getTime()),
                        "test_age",
                        "test_postCode",
                        "test_address",
                        Long.toString(new Date().getTime()),
                        userName,
                        "test_password",
                        new Role(userName, RoleTypes.ROLE_USER.toString()),
                        null); // Orders
    }

    public UserEntity getNewUSerEntity() {

        String userName = String.format("test_login%d", new Date().getTime());
        return new UserEntity("test_firstName",
                              "test_lastName",
                              String.format("test_email%d@gmail.com", new Date().getTime()),
                              "test_age",
                              "test_postCode",
                              "test_address",
                              new Date().getTime(),
                              userName,
                              "test_password",
                              null,
                              null);
    }

    @Transactional
    private UserEntity getPersistedUserEntity() {
        /*
            setup for test - boiler plate code
         */
        Session session = sessionFactory.getCurrentSession();
        UserEntity userEntity = getNewUSerEntity();
        RoleEntity roleEntity = new RoleEntity(userEntity.getLogin(), RoleTypes.ROLE_USER);
        userEntity.setRole(roleEntity);
        Long userID = (Long) session.save(userEntity);
        session.save(roleEntity);
        userEntity.setId(userID);
        return userEntity;
    }
}
