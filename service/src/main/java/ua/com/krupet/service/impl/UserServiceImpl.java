package ua.com.krupet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public List<User> getUsersList() {
        return usersDAO.getUsersList();
    }
}
