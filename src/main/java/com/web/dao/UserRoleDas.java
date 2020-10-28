package com.web.dao;

import com.web.model.UserRole;
import com.web.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserRoleDas implements UserRoleDao {

    @Override
    public UserRole getByName(String name) {
        UserRole role = new UserRole();
        String sql = String.format("select ers_user_role_id," +
                "ers_role from ers_reimbursment_roles where ers_role = ?");
            try(Connection connection = ConnectionUtil.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setString(1,name);
                ResultSet resultSet = ps.executeQuery();
                if (resultSet.next()){
                    role.setId(resultSet.getInt(1));
                    role.setRole(resultSet.getString(2));
                }else {
                    role = null;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        return role;
    }

    @Override
    public UserRole getById(int id) {
        UserRole role = new UserRole();
        String sql = String.format("select ers_user_role_id," +
                "ers_role from ers_reimbursment_roles where ers_user_role_id = ?");
        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                role.setId(resultSet.getInt(1));
                role.setRole(resultSet.getString(2));
            }else {
                role = null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return role;
    }
}
