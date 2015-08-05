package ua.com.krupet.dao;

import ua.com.krupet.User;

import java.util.List;

/**
 * Defines interface for users service that are able to retrieve users information from DataBase (DB)
 * Note in service I am using DTO objects instead of entities to avoid some lazy initialization/loading
 * problems with JPA provider
 *
 * @author krupet
 * @see ua.com.krupet.User
 * @see ua.com.krupet.Role
 * @see ua.com.krupet.RoleTypes
 */
public interface UsersDAO {

    /**
     * Creates new user in DB
     *
     * @param user - DTO representing new user to persist
     * @return DTO representing persisted user
     */
    User createUser(User user);

    /**
     * Updates users information
     *
     * @param user - DTO representing persisted user to update
     * @return DTO representing updated user
     */
    User updateUser(User user);

    /**
     * Retrieves user from DB by its ID
     *
     * @param userID - ID of required user
     * @return DTO representing required user
     */
    User getUserByID(Long userID);

    /**
     * Retrieves user by its username (login)
     * @param userName - username of required user
     * @return DTO representing required user
     */
    User getUserByUserName(String userName);

    /**
     * Retrieves list of all users in DB
     * @return list of users
     */
    List<User> getUsersList();
}
