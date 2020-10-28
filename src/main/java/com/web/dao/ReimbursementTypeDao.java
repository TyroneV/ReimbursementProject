package com.web.dao;

import com.web.model.ReimbursementType;

public interface ReimbursementTypeDao {

    ReimbursementType getByName(String name);

    ReimbursementType getById(int id);
}
