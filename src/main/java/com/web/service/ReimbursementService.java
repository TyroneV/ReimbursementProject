package com.web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.dao.ReimbursementDao;
import com.web.dao.ReimbursementDas;
import com.web.model.Reimbursement;
import com.web.model.ReimbursementStatus;
import com.web.model.User;

public class ReimbursementService {

    private final ReimbursementDao rDao = new ReimbursementDas();

    public String getPastTickets(User user) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(rDao.getAllReimbursementUser(user));
    }

    public String addRequest(Reimbursement reimbursement) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(rDao.createReimbursement(reimbursement));
    }

    public String getAllReimbursement() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(rDao.getAllReimbursement());
    }

    public String filterByStatus(ReimbursementStatus reimbursementStatus) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(rDao.getAllReimbursementStatus(reimbursementStatus));
    }

    public String changeStatus(Reimbursement reimbursement) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(rDao.updateReimbursement(reimbursement));
    }
}
