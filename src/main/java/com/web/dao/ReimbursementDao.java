package com.web.dao;

import com.web.model.Reimbursement;
import com.web.model.ReimbursementStatus;
import com.web.model.User;

import java.util.List;

public interface ReimbursementDao {

    Reimbursement createReimbursement(Reimbursement reimbursement);

    List<Reimbursement> getAllReimbursement();

    List<Reimbursement> getAllReimbursementStatus(ReimbursementStatus status);

    List<Reimbursement> getAllReimbursementUser(User user);

    Reimbursement getReimbursementById(int id);

    Reimbursement updateReimbursement(Reimbursement reimbursement);

    Reimbursement deleteReimbursement(Reimbursement reimbursement);
}
