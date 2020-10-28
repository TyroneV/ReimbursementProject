package com.web.dao;

import com.web.model.UserRole;

public interface UserRoleDao {

    UserRole getByName(String name);
    UserRole getById(int id);
}
