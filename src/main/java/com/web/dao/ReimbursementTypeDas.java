package com.web.dao;

import com.web.model.ReimbursementType;
import com.web.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReimbursementTypeDas implements ReimbursementTypeDao {

    @Override
    public ReimbursementType getByName(String name) {
        ReimbursementType reimbursementType = new ReimbursementType();
        String sql = String.format("select reimb_type_id ,reimb_type " +
                "from ers_reimbursment_type ert where reimb_type = ?;");
        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1,name);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                reimbursementType.setId(resultSet.getInt(1));
                reimbursementType.setType(resultSet.getString(2));
            }else {
                reimbursementType = null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return reimbursementType;
    }

    @Override
    public ReimbursementType getById(int id) {
        ReimbursementType reimbursementType = new ReimbursementType();
        String sql = String.format("select reimb_type_id ,reimb_type " +
                "from ers_reimbursment_type ert where reimb_type_id = ?;");
        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                reimbursementType.setId(resultSet.getInt(1));
                reimbursementType.setType(resultSet.getString(2));
            }else {
                reimbursementType = null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return reimbursementType;
    }
}
