package com.web.dao;

import com.web.model.Reimbursement;
import com.web.model.ReimbursementStatus;
import com.web.model.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class ReimbursementDasTest {

    ReimbursementDao reimbursementDao = new ReimbursementDas();


    @Test
    void createReimbursement() {
    }

    @Test
    void getAllReimbursement() {
        assertTrue(reimbursementDao.getAllReimbursement() != null);
    }

    @Test
    void getAllReimbursementStatus() {
        ReimbursementStatus rs = new ReimbursementStatus();
        rs.setId(1);
        assertTrue(reimbursementDao.getAllReimbursementStatus(rs) != null);
    }

    @Test
    void getAllReimbursementUser() {
        User user = new User();
        user.setUsername("Testname");
        user.setPassword("password");
        assertTrue(reimbursementDao.getAllReimbursementUser(user) != null);
    }

    @Test
    void getReimbursementById() {
    }

    @Test
    void updateReimbursement() {
    }

    @Test
    void deleteReimbursement() {
    }
}