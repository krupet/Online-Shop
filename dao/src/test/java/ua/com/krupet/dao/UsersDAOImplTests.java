package ua.com.krupet.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.com.krupet.BaseDaoTest;
import ua.com.krupet.User;

import java.util.Date;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by krupet on 11.07.2015.
 */
public class UsersDAOImplTests extends BaseDaoTest{

    @Autowired
    private UsersDAO usersDAO;

    @Test
    @Transactional
    public void testPostNewUserAndExpectedIsOk() {
        User newCustomer = getTestUser();
        User dbCustomer = usersDAO.createUser(newCustomer);
        assertNotNull(dbCustomer);
        assertNotNull(dbCustomer.getId());
    }

    private User getTestUser() {
        /*
            using String.format to avoid collisions in some values
            where in db properties is unique = true
         */
        return new User("test_firstName",
                        "test_lastName",
                        String.format("test_email%d@gmail.com", new Date().getTime()),
                        "test_age",
                        "test_postCode",
                        "test_address",
                        Long.toString(new Date().getTime()),
                        String.format("test_login%d", new Date().getTime()),
                        "test_password",
                        null,                         // Role
                        null);                        // Orders
    }
}
