/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.User;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import DAO.DbOperations.SqlOperation;
import java.sql.ResultSet;
/**
 *
 * @author Ngoc Thao
 */
public class UserDAO{

    public static void insert(final User obj) {
        final int userId = DbOperations.getNextId("USER", "user_id");

        obj.setUser_id(userId);

        SqlOperation[] operations = new SqlOperation[] {
            new SqlOperation() {
                @Override
                public String getSql() {
                    return "INSERT INTO USER(user_id, username, password, role, created_at) VALUES (?, ?, ?, ?, ?)";
                }

                @Override
                public void setParameters(PreparedStatement ps) throws Exception {
                    ps.setInt(1, userId);
                    ps.setString(2, obj.getUsername());
                    ps.setString(3, obj.getPassword());
                    ps.setString(4, obj.getRole());
                    ps.setTimestamp(5, Timestamp.valueOf(obj.getCreate_at()));
                }
            }
        };

        DbOperations.executeTransaction(operations, "Thêm tài khoản thành công!");
    }

    public static User login(final String userName, final String password) {
        User user = null;
        try {
            ResultSet rs = DbOperations.getData("SELECT * FROM USER WHERE username = '" + userName + "' and password = '" + password +"'");
            if (rs.next()) {
                boolean isActive = rs.getBoolean("isActive");
                if (isActive) {
                    user = new User();
                    user.setUser_id(rs.getInt("user_id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setRole(rs.getString("role"));
                    user.setCreate_at(rs.getTimestamp("created_at").toLocalDateTime());
                    user.setIsActive(isActive);
                }
            }
            rs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return user;
    }

    public static void update(User obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static User getById(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static List<User> getAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
