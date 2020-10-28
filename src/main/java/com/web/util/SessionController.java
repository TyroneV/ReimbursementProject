package com.web.util;

import com.web.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionController {

    public void setSession(HttpServletRequest req, User user) {
        HttpSession session = req.getSession();
        session.setAttribute("user", user);
    }

    public User getSessionUser(HttpServletRequest req) {
        return (User)req.getSession().getAttribute("user");
    }

    public void invalidate(HttpServletRequest req) {
        req.getSession().invalidate();
    }
}
