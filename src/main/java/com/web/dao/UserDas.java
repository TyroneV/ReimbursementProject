package com.web.dao;

import com.web.model.User;
import com.web.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDas implements UserDao{

    private final UserRoleDao userRoleDao = new UserRoleDas();

    @Override
    public User createUser(User user) {

        String sql = String.format("insert into ers_users" +
                "(ers_username,ers_password,user_first_name,user_last_name,user_email,user_role_id)\n" +
                "values " +
                "(?,?,?,?,?,?)" +
                "returning  ers_users_id,ers_username,ers_password,user_first_name,user_last_name,user_email,user_role_id;");
        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getFirstName());
            ps.setString(4,user.getLastName());
            ps.setString(5,user.getEmail());
            ps.setInt(6,user.getUserRole().getId());
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                user.setId(resultSet.getInt("ers_users_id"));
            }else {
                user = null;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {

        List<User> userList = new ArrayList<>();
        String sql = String.format("select * from usertable;");
        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setFirstName(resultSet.getString("firstname"));
                user.setLastName(resultSet.getString("lastname"));
                user.setEmail(resultSet.getString("email"));
                user.setUserRole(userRoleDao.getByName(resultSet.getString("role")));
                userList.add(user);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public User getUserById(User user) {
        String sql = String.format("select * from usertable where id = ?;");
        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1,user.getId());
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setFirstName(resultSet.getString("firstname"));
                user.setLastName(resultSet.getString("lastname"));
                user.setEmail(resultSet.getString("email"));
                user.setUserRole(userRoleDao.getByName(resultSet.getString("role")));
            }else {
                user = null;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUserByUsername(User user) {
        String sql = String.format("select * from usertable where username = ?;");
        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1,user.getUsername());
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setFirstName(resultSet.getString("firstname"));
                user.setLastName(resultSet.getString("lastname"));
                user.setEmail(resultSet.getString("email"));
                user.setUserRole(userRoleDao.getByName(resultSet.getString("role")));
            }else {
                user = null;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUserByUsernameAndPassword(User user) {
        String sql = String.format("select * from usertable where username = ? and password = ?;");
        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setFirstName(resultSet.getString("firstname"));
                user.setLastName(resultSet.getString("lastname"));
                user.setEmail(resultSet.getString("email"));
                user.setUserRole(userRoleDao.getByName(resultSet.getString("role")));
            }else {
                user = null;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    //TODO
    @Override
    public User updateUser(User user) {
        return null;
    }

    //TODO
    @Override
    public User deleteUser(User user) {
        return null;
    }
}
