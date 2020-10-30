package com.web.dao;

import com.web.model.*;
import com.web.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDas implements ReimbursementDao {

    private final UserDao userDao = new UserDas();
    private final ReimbursementTypeDao reimbursementTypeDao = new ReimbursementTypeDas();
    private final ReimbursementStatusDao reimbursementStatusDao = new ReimbursementStatusDas();

    @Override
    public Reimbursement createReimbursement(Reimbursement reimbursement) {
        String sql = String.format("insert into dbr.ers_reimbursement \n" +
                "(reimb_amount ,reimb_description,reimb_author_id,reimb_status_id,reimb_type_id)\n" +
                "values\n" +
                "(?,?,?,?,?) \n" +
                "returning reimb_id,reimb_amount,reimb_submitted,reimb_resolved ,reimb_description,reimb_author_id,reimb_resolver_id,reimb_status_id,reimb_type_id;");

        User u = new User();
        ReimbursementStatus rs = new ReimbursementStatus();
        ReimbursementType rt = new ReimbursementType();

        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setDouble(1,reimbursement.getAmount());
            ps.setString(2,reimbursement.getDescription());
            ps.setInt(3,reimbursement.getAuthor().getId());
            ps.setInt(4,1);
            ps.setInt(5,reimbursement.getType().getId());
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                reimbursement.setId(resultSet.getInt(1));
                reimbursement.setAmount(resultSet.getDouble(2));
                reimbursement.setSubmittedDate(resultSet.getTimestamp(3));
                reimbursement.setDescription(resultSet.getString(5));
                u.setId(resultSet.getInt(6));
                rs.setId(resultSet.getInt(8));
                rt.setId(resultSet.getInt(9));
            }else {
                reimbursement = null;
            }
        }catch (Exception e){
            e.printStackTrace();
            reimbursement = null;
        }
        if(reimbursement != null) {
            u = userDao.getUserById(u);
            rs = reimbursementStatusDao.getById(rs.getId());
            rt = reimbursementTypeDao.getById(rt.getId());
            reimbursement.setAuthor(u);
            reimbursement.setStatus(rs);
            reimbursement.setType(rt);
        }
        return reimbursement;
    }

    @Override
    public List<Reimbursement> getAllReimbursement() {
            String sql = String.format("select * from dbr.ers_reimbursement order by reimb_id;");
        List<Reimbursement> reimbursementList = new ArrayList<>();
        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                Reimbursement reimbursement = new Reimbursement();
                User a = new User();
                User r = new User();
                ReimbursementStatus rs = new ReimbursementStatus();
                ReimbursementType rt = new ReimbursementType();

                reimbursement.setId(resultSet.getInt(1));
                reimbursement.setAmount(resultSet.getDouble(2));
                reimbursement.setSubmittedDate(resultSet.getTimestamp(3));
                reimbursement.setResolvedDate(resultSet.getTimestamp(4));
                reimbursement.setDescription(resultSet.getString(5));
                a.setId(resultSet.getInt(6));
                r.setId(resultSet.getInt(7));
                rs.setId(resultSet.getInt(8));
                rt.setId(resultSet.getInt(9));
                reimbursement.setAuthor(a);
                reimbursement.setResolver(r);
                reimbursement.setStatus(rs);
                reimbursement.setType(rt);
                reimbursementList.add(reimbursement);
            }
        }catch (Exception e){
            e.printStackTrace();
            reimbursementList = null;
        }
        return completeReimbursementList(reimbursementList);
    }

    @Override
    public List<Reimbursement> getAllReimbursementStatus(ReimbursementStatus status) {
        String sql = String.format("select * from dbr.ers_reimbursement where reimb_status_id = ? order by reimb_id;");
        List<Reimbursement> reimbursementList = new ArrayList<>();
        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,status.getId());
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                Reimbursement reimbursement = new Reimbursement();
                User a = new User();
                User r = new User();
                ReimbursementStatus rs = new ReimbursementStatus();
                ReimbursementType rt = new ReimbursementType();

                reimbursement.setId(resultSet.getInt(1));
                reimbursement.setAmount(resultSet.getDouble(2));
                reimbursement.setSubmittedDate(resultSet.getTimestamp(3));
                reimbursement.setResolvedDate(resultSet.getTimestamp(4));
                reimbursement.setDescription(resultSet.getString(5));
                a.setId(resultSet.getInt(6));
                r.setId(resultSet.getInt(7));
                rs.setId(resultSet.getInt(8));
                rt.setId(resultSet.getInt(9));
                reimbursement.setAuthor(a);
                reimbursement.setResolver(r);
                reimbursement.setStatus(rs);
                reimbursement.setType(rt);
                reimbursementList.add(reimbursement);
            }
        }catch (Exception e){
            e.printStackTrace();
            reimbursementList = null;
        }
        return completeReimbursementList(reimbursementList);
    }

    @Override
    public List<Reimbursement> getAllReimbursementUser(User user) {
        String sql = String.format("select * from dbr.ers_reimbursement where reimb_author_id = ? order by reimb_id;");
        List<Reimbursement> reimbursementList = new ArrayList<>();
        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,user.getId());
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                Reimbursement reimbursement = new Reimbursement();
                User a = new User();
                User r = new User();
                ReimbursementStatus rs = new ReimbursementStatus();
                ReimbursementType rt = new ReimbursementType();

                reimbursement.setId(resultSet.getInt(1));
                reimbursement.setAmount(resultSet.getDouble(2));
                reimbursement.setSubmittedDate(resultSet.getTimestamp(3));
                reimbursement.setResolvedDate(resultSet.getTimestamp(4));
                reimbursement.setDescription(resultSet.getString(5));
                a.setId(resultSet.getInt(6));
                r.setId(resultSet.getInt(7));
                rs.setId(resultSet.getInt(8));
                rt.setId(resultSet.getInt(9));
                reimbursement.setAuthor(a);
                reimbursement.setResolver(r);
                reimbursement.setStatus(rs);
                reimbursement.setType(rt);
                reimbursementList.add(reimbursement);
            }
        }catch (Exception e){
            e.printStackTrace();
            reimbursementList = null;
        }
        return completeReimbursementList(reimbursementList);
    }

    @Override//TODO
    public Reimbursement getReimbursementById(int id) {
        return null;
    }

    @Override
    public Reimbursement updateReimbursement(Reimbursement reimbursement) {
        String sql = String.format("update dbr.ers_reimbursement set reimb_resolved = localtimestamp " +
                ",reimb_resolver_id = ?, reimb_status_id = ? where reimb_id = ? \n" +
                "returning reimb_id,reimb_amount,reimb_submitted,reimb_resolved ," +
                "reimb_description,reimb_author_id,reimb_resolver_id,reimb_status_id,reimb_type_id;");
        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,reimbursement.getResolver().getId());
            ps.setInt(2,reimbursement.getStatus().getId());
            ps.setInt(3,reimbursement.getId());
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                User a = new User();
                User r = new User();
                ReimbursementStatus rs = new ReimbursementStatus();
                ReimbursementType rt = new ReimbursementType();

                reimbursement.setId(resultSet.getInt(1));
                reimbursement.setAmount(resultSet.getDouble(2));
                reimbursement.setSubmittedDate(resultSet.getTimestamp(3));
                reimbursement.setResolvedDate(resultSet.getTimestamp(4));
                reimbursement.setDescription(resultSet.getString(5));
                a.setId(resultSet.getInt(6));
                r.setId(resultSet.getInt(7));
                rs.setId(resultSet.getInt(8));
                rt.setId(resultSet.getInt(9));
                reimbursement.setAuthor(a);
                reimbursement.setResolver(r);
                reimbursement.setStatus(rs);
                reimbursement.setType(rt);
            }
        }catch (Exception e){
            e.printStackTrace();
            reimbursement = null;
        }
        return completeReimbursement(reimbursement);
    }

    @Override//TODO
    public Reimbursement deleteReimbursement(Reimbursement reimbursement) {
        return null;
    }

    private List<Reimbursement> completeReimbursementList(List<Reimbursement> reimbursementList){
        if(reimbursementList!=null) {
            for (int i = 0; i < reimbursementList.size(); i++) {
                reimbursementList.set(i, completeReimbursement(reimbursementList.get(i)));
            }
        }
        return reimbursementList;
    }

    private Reimbursement completeReimbursement(Reimbursement reimbursement){
        if(reimbursement != null) {
            reimbursement.setAuthor(
                    userDao.getUserById(reimbursement.getAuthor())
            );
            reimbursement.setResolver(
                    userDao.getUserById(reimbursement.getResolver())
            );
            reimbursement.setType(
                    reimbursementTypeDao.getById(reimbursement.getType().getId())
            );
            reimbursement.setStatus(
                    reimbursementStatusDao.getById(reimbursement.getStatus().getId())
            );
        }
        return reimbursement;
    }
}
