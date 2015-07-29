package ua.com.krupet.service;

import ua.com.krupet.User;

import java.util.List;

/**
 * Created by krupet on 7/21/15.
 */
public interface UserService {

    User createUser(User user);
    User updateUser(User user);
    User getUserByID(Long userID);
    List<User> getUsersList();
}
