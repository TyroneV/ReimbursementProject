package com.web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.dao.UserDao;
import com.web.dao.UserDas;
import com.web.model.User;
import com.web.security.PasswordHasher;

public class UserService {

    private final UserDao userDao = new UserDas();

    public String getAllUsers() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(userDao.getAllUsers());
    }

    public boolean createAccount(User user){
        boolean success = false;
        user.setPassword(PasswordHasher.generateHash(user.getPassword()));
        if(userDao.getUserByUsername(user) == null
                && userDao.createUser(user) != null){
            success = true;
        }
        return success;
    }

    public boolean login(User user){
        boolean success = false;
        User dbUser = new User();
        dbUser.setUsername(user.getUsername());
        dbUser = userDao.getUserByUsername(dbUser);
        if(dbUser != null){
            user.setPassword(PasswordHasher.getHashed(user.getPassword(),dbUser.getPassword()));
            if(user.getPassword().equals(dbUser.getPassword())){
                userDao.getUserByUsernameAndPassword(user);
                success = true;
            }
        }
        return success;
    }

}
