package com.web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.web.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    UserService userService = new UserService();

    @Test
    void getAllUsers() throws JsonProcessingException {
        assertTrue(userService.getAllUsers() != null);
    }

    @Test
    void createAccount() {
    }

    @Test
    void login() {

        User user = new User();
        user.setUsername("Testname");
        user.setPassword("password");
        assertTrue(userService.login(user));
    }


}