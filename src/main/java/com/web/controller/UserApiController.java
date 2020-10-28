package com.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.model.User;
import com.web.service.UserService;
import com.web.util.SessionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserApiController {

    private final SessionController sc = new SessionController();
    private final UserService userService = new UserService();

    public void getAllUser(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        if(req.getMethod().toLowerCase().equals("get")) {
            resp.setContentType("text/json");
            try {
                resp.getWriter().println(userService.getAllUsers());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            resp.sendError(400);
        }
    }

    public void createUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(req.getMethod().toLowerCase().equals("post")) {
            User user = new ObjectMapper().readValue(req.getInputStream(), User.class);
            if (!userService.createAccount(user)) {
                resp.sendError(406, "Unable to create account");
            } else {
                System.out.println("User created");
            }
        } else {
            resp.sendError(400);
        }
    }

    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(req.getMethod().toLowerCase().equals("post")) {
            User user = new ObjectMapper().readValue(req.getInputStream(), User.class);
            if (userService.login(user) && sc.getSessionUser(req) == null) {
                sc.setSession(req,user);
                System.out.println("Login Successful");
            }
            else if(sc.getSessionUser(req) != null){
                System.out.println("Already logged in!");
            }
            else {
                resp.sendError(406, "Unable to Login");
            }
        } else {
            resp.sendError(400);
        }
    }

    public void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(req.getMethod().toLowerCase().equals("get")) {
            if (sc.getSessionUser(req) == null) {
                resp.sendError(400, "Not logged in");
            } else {
                sc.invalidate(req);
                System.out.println("Logged out");
            }
        } else {
            resp.sendError(400);
        }
    }
}
