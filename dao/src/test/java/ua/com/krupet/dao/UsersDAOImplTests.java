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

    private static long count = 0;

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

        count++;
        /*
            using String.format to avoid collisions in some values
            where in db properties is unique = true
         */
        return new User(String.format("test_firstName_%d", count),
                        "test_lastName",
                        "test_email@gmail.com",
                        "test_age",
                        "test_postCode",
                        "test_address",
                        new Date().getTime(),
                        "test_login",
                        "test_password",
                        null,                         // Role
                        null);                        // Orders
    }
}
