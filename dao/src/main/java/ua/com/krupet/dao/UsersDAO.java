package ua.com.krupet.dao;

import ua.com.krupet.User;

import java.util.List;

/**
 * Created by krupet on 11.07.2015.
 */
public interface UsersDAO {

    User createUser(User user);
    List<User> getUsersList();
}
