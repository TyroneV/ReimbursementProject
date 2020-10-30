package com.web.dao;

import com.web.model.ReimbursementStatus;
import com.web.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReimbursementStatusDas implements ReimbursementStatusDao {

    @Override
    public ReimbursementStatus getByName(String name) {
        ReimbursementStatus reimbursementStatus = new ReimbursementStatus();
        String sql = String.format("select reimb_status_id ,reimb_status " +
                "from dbr.ers_reimbursment_status ers where reimb_status = ?;");
        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1,name);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                reimbursementStatus.setId(resultSet.getInt(1));
                reimbursementStatus.setStatus(resultSet.getString(2));
            }else {
                reimbursementStatus = null;
            }
        }catch (Exception e){
            e.printStackTrace();
            reimbursementStatus = null;
        }
        return reimbursementStatus;
    }

    @Override
    public ReimbursementStatus getById(int id) {
        ReimbursementStatus reimbursementStatus = new ReimbursementStatus();
        String sql = String.format("select reimb_status_id ,reimb_status " +
                "from dbr.ers_reimbursment_status ers where reimb_status_id = ?;");
        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                reimbursementStatus.setId(resultSet.getInt(1));
                reimbursementStatus.setStatus(resultSet.getString(2));
            }else {
                reimbursementStatus = null;
            }
        }catch (Exception e){
            e.printStackTrace();
            reimbursementStatus = null;
        }
        return reimbursementStatus;
    }
}
