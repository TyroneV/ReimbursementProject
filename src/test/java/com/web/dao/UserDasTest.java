package com.web.dao;

import com.web.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDasTest {

    UserDao userDao = new UserDas();
    @Test
    void createUser() {
    }

    @Test
    void getAllUsers() {
        assertTrue(userDao.getAllUsers() != null);
    }

    @Test
    void getUserById() {
        User user = new User();
        user.setId(1);
        assertTrue(userDao.getUserById(user) != null);
    }

    @Test
    void getUserByUsername() {
        User user = new User();
        user.setUsername("Testname");
        assertTrue(userDao.getUserByUsername(user) != null);
    }

    @Test
    void getUserByUsernameAndPassword() {
        User user = new User();
        user.setUsername("Testname");
        user.setPassword("b1a86dd6f6c79be42208da157f35535e.+cmxaDJSayO3GpQ97R4TeQ==");
        assertTrue(userDao.getUserByUsernameAndPassword(user) != null);
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }
}