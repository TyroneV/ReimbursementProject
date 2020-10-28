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
    public void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserApiController userApiController = new UserApiController();
        ReimbursementApiController reimbursementApiController = new ReimbursementApiController();

        String uri = req.getRequestURI();
        String regex = "/reimbursement";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(uri);
        uri = m.replaceAll("");
        switch (uri){
            case "/login":
                System.out.println("login");
                break;
            case "/api/login":
                userApiController.login(req,resp);
                break;
            case "/api/logout":
                userApiController.logout(req,resp);
                break;
            case "/api/users":
                userApiController.getAllUser(req,resp);
                break;
            case "/api/register":
                userApiController.createUser(req,resp);
                break;
            case "/api/ticket/add":
                reimbursementApiController.newTicket(req,resp);
                break;
            case "/api/ticket/past":
                reimbursementApiController.pastTickets(req,resp);
                break;
            case "/api/ticket/all":
                reimbursementApiController.getAllTickets(req,resp);
                break;
            case "/api/ticket/filter":
                reimbursementApiController.filteredTickets(req,resp);
                break;
            case "/api/ticket/update":
                reimbursementApiController.updateTicket(req,resp);
                break;
            default:
                System.out.println("Home");
                break;
        }
    }
}
