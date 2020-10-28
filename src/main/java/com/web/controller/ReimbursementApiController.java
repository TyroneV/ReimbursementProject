package com.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.model.Reimbursement;
import com.web.model.ReimbursementStatus;
import com.web.service.ReimbursementService;
import com.web.util.SessionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReimbursementApiController {

    private SessionController sc = new SessionController();
    private final ReimbursementService rs = new ReimbursementService();

    public void pastTickets(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(req.getMethod().toLowerCase().equals("get") &&
                sc.getSessionUser(req).getUserRole().getRole().equals("Employee")) {
            resp.setContentType("text/json");
            try {
                if(sc.getSessionUser(req) != null) {
                    resp.getWriter().println(rs.getPastTickets(sc.getSessionUser(req)));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            resp.sendError(400);
        }
    }

    public void newTicket(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(req.getMethod().toLowerCase().equals("post")) {
            resp.setContentType("text/json");
            Reimbursement reimbursement =
                    new ObjectMapper().readValue(req.getInputStream(), Reimbursement.class);
            try {
                if(sc.getSessionUser(req) != null &&
                        sc.getSessionUser(req).getUserRole().getRole().equals("Employee")) {
                    reimbursement.setAuthor(sc.getSessionUser(req));
                    resp.getWriter().println(rs.addRequest(reimbursement));
                }else {
                    resp.sendError(401);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            resp.sendError(400);
        }
    }

    public void getAllTickets(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(req.getMethod().toLowerCase().equals("get")) {
            resp.setContentType("text/json");
            try {
                if(sc.getSessionUser(req) != null &&
                        sc.getSessionUser(req).getUserRole().getRole().equals("Finance Manager")) {
                    resp.getWriter().println(rs.getAllReimbursement());
                }else {
                    resp.sendError(403);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            resp.sendError(400);
        }
    }

    public void filteredTickets(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(req.getMethod().toLowerCase().equals("get")) {
            resp.setContentType("text/json");
            try {
                if(sc.getSessionUser(req) != null &&
                        sc.getSessionUser(req).getUserRole().getRole().equals("Finance Manager") &&
                        req.getParameter("status") != null) {
                    ReimbursementStatus status = new ReimbursementStatus();
                    status.setId(Integer.parseInt(req.getParameter("status")));
                    resp.getWriter().println(rs.filterByStatus(status));
                }else {
                    resp.sendError(403);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            resp.sendError(400);
        }
    }

    public void updateTicket(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(req.getMethod().toLowerCase().equals("post")) {
            resp.setContentType("text/json");
            try {
                if(sc.getSessionUser(req) != null &&
                        sc.getSessionUser(req).getUserRole().getRole().equals("Finance Manager")) {
                    Reimbursement reimbursement =
                            new ObjectMapper().readValue(req.getInputStream(), Reimbursement.class);
                    reimbursement.setResolver(sc.getSessionUser(req));
                    resp.getWriter().println(rs.changeStatus(reimbursement));
                }else {
                    resp.sendError(403);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            resp.sendError(400);
        }
    }
}
