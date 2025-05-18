/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.User;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import DAO.DbOperations.SqlOperation;
import java.sql.ResultSet;
import java.util.ArrayList;
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
                    return "INSERT INTO USER(user_id, username, password, role, created_at, isActive) VALUES (?, ?, ?, ?, ?, ?)";
                }

                @Override
                public void setParameters(PreparedStatement ps) throws Exception {
                    ps.setInt(1, userId);
                    ps.setString(2, obj.getUsername());
                    ps.setString(3, obj.getPassword());
                    ps.setString(4, obj.getRole());
                    ps.setTimestamp(5, Timestamp.valueOf(obj.getCreate_at()));
                    ps.setBoolean(6, obj.isIsActive());
                }
            }
        };

        DbOperations.executeTransaction(operations, "Thêm tài khoản thành công!");
    }

    public static boolean checkUserName(String userName) {
        boolean exists = false;
        try{
            ResultSet rs = DbOperations.getData("SELECT * FROM USER WHERE username = '" + userName + "'");
            if(rs.next()) {
                exists = true;
            }
            rs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return exists;
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

    /**
     * Get subscriber information for the current user session
     * @param userId The user ID to get subscriber information for
     * @return true if subscriber information was found and set, false otherwise
     */
    public static boolean getSubscriberForSession(int userId) {
        try {
            // Get subscriber information for the user
            Model.Subscriber subscriber = SubscriberDAO.getByUserId(userId);

            // Set the subscriber in the session
            if (subscriber != null) {
                internet.mangement.system.Session.UserSession.setCurrentSub(subscriber);
                return true;
            } else {
                System.err.println("Warning: No subscriber found for user ID " + userId);
                return false;
            }
        } catch (Exception ex) {
            System.err.println("Error getting subscriber information: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean update(User obj) {
        try {
            StringBuilder query = new StringBuilder("UPDATE USER SET ");
            boolean needComma = false;

            // Only include fields that are not null
            if (obj.getUsername() != null) {
                query.append("username = '").append(obj.getUsername()).append("'");
                needComma = true;
            }

            if (obj.getPassword() != null) {
                if (needComma) query.append(", ");
                query.append("password = '").append(obj.getPassword()).append("'");
                needComma = true;
            }

            if (obj.getRole() != null) {
                if (needComma) query.append(", ");
                query.append("role = '").append(obj.getRole()).append("'");
                needComma = true;
            }

            // Check if isActive is explicitly set using a separate flag
            boolean isActiveSet = false;
            try {
                // This will throw NullPointerException if isActive is not set
                boolean isActive = obj.isIsActive();
                isActiveSet = true;

                if (needComma) query.append(", ");
                query.append("isActive = ").append(isActive);
                needComma = true;
            } catch (Exception e) {
                // isActive is not set, ignore
            }

            // If no fields to update, return false
            if (!needComma) {
                return false;
            }

            query.append(" WHERE user_id = ").append(obj.getUser_id());

            DbOperations.setDataOrDelete(query.toString(), "");
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static User getById(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static ArrayList<User> getAll() {
        ArrayList<User> userList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("SELECT * FROM USER");
            while (rs.next()) {
                User user = new User();
                user.setUser_id(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setCreate_at(rs.getTimestamp("created_at").toLocalDateTime());
                user.setIsActive(rs.getBoolean("isActive"));
                userList.add(user);
            }
            rs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return userList;
    }

    public static ArrayList<User> getByCondition(String username, String role, String status, java.util.Date startDate, java.util.Date endDate) {
        ArrayList<User> userList = new ArrayList<>();
        try {
            StringBuilder query = new StringBuilder("SELECT * FROM USER WHERE 1=1");

            if (username != null && !username.isEmpty()) {
                query.append(" AND username LIKE '%").append(username).append("%'");
            }

            if (role != null && !role.isEmpty() && !role.equals("Tất cả")) {
                query.append(" AND role = '").append(role).append("'");
            }

            if (status != null && !status.isEmpty() && !status.equals("Tất cả")) {
                boolean isActive = status.equals("Hoạt động");
                query.append(" AND isActive = ").append(isActive);
            }

            if (startDate != null && endDate != null) {
                java.sql.Timestamp startTimestamp = new java.sql.Timestamp(startDate.getTime());
                java.sql.Timestamp endTimestamp = new java.sql.Timestamp(endDate.getTime());
                query.append(" AND created_at >= '").append(startTimestamp).append("' AND create_at <= '").append(endTimestamp).append("'");
            } else if (startDate != null) {
                java.sql.Timestamp startTimestamp = new java.sql.Timestamp(startDate.getTime());
                query.append(" AND created_at >= '").append(startTimestamp).append("'");
            } else if (endDate != null) {
                java.sql.Timestamp endTimestamp = new java.sql.Timestamp(endDate.getTime());
                query.append(" AND created_at <= '").append(endTimestamp).append("'");
            }

            ResultSet rs = DbOperations.getData(query.toString());
            while (rs.next()) {
                User user = new User();
                user.setUser_id(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setCreate_at(rs.getTimestamp("created_at").toLocalDateTime());
                user.setIsActive(rs.getBoolean("isActive"));
                userList.add(user);
            }
            rs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return userList;
    }

}
