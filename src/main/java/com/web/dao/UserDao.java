package com.web.dao;

import com.web.model.User;

import java.util.List;

public interface UserDao {

    User createUser(User user);

    List<User> getAllUsers();

    User getUserById(User user);

    User getUserByUsername(User user);

    User getUserByUsernameAndPassword(User user);

    User updateUser(User user);

    User deleteUser(User user);

}
