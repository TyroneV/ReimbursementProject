package com.web.servlet;

import com.web.controller.ReimbursementApiController;
import com.web.controller.UserApiController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestHandler {

    public void data(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserApiController userApiController = new UserApiController();
        ReimbursementApiController reimbursementApiController = new ReimbursementApiController();

        String uri = req.getRequestURI();
        String regex = "/reimbursement";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(uri);
        uri = m.replaceAll("");
        switch (uri){
            case "/api/login.json":
                userApiController.login(req,resp);
                break;
            case "/api/logout.json":
                userApiController.logout(req,resp);
                break;
            case "/api/users.json":
                userApiController.getAllUser(req,resp);
                break;
            case "/api/register.json":
                userApiController.createUser(req,resp);
                break;
            case "/api/ticket/add.json":
                reimbursementApiController.newTicket(req,resp);
                break;
            case "/api/ticket/past.json":
                reimbursementApiController.pastTickets(req,resp);
                break;
            case "/api/ticket/all.json":
                reimbursementApiController.getAllTickets(req,resp);
                break;
            case "/api/ticket/filter.json":
                reimbursementApiController.filteredTickets(req,resp);
                break;
            case "/api/ticket/update.json":
                reimbursementApiController.updateTicket(req,resp);
                break;
        }
    }

    public String routes(HttpServletRequest req) {
        String uri = req.getRequestURI();
        String regex = "/reimbursement";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(uri);
        uri = m.replaceAll("");

        switch (uri){
            case  "/home.page" :

            default:
                return "html/index.html";
        }
    }
}
