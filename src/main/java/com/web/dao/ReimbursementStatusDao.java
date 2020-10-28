package com.web.dao;

import com.web.model.ReimbursementStatus;

public interface ReimbursementStatusDao {

    ReimbursementStatus getByName(String name);

    ReimbursementStatus getById(int id);
}
