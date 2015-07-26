package ua.com.krupet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ua.com.krupet.User;
import ua.com.krupet.dao.UsersDAO;
import ua.com.krupet.service.UserService;

import java.util.List;

/**
 * Created by krupet on 7/26/15.
 */
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersDAO usersDAO;

    @Override
    public List<User> getUsersList() {
        return usersDAO.getUsersList();
    }
}
