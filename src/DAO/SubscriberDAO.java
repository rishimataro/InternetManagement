/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Subscriber;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import DAO.DbOperations.SqlOperation;

/**
 *
 * @author Ngoc Thao
 */
public class SubscriberDAO{

    public static void insert(final Subscriber obj) {
        final int userId = DbOperations.getNextId("USER", "user_id");
        final int subscriberId = DbOperations.getNextId("SUBSCRIBER", "subscriber_id");

        obj.setUser_id(userId);
        obj.setSubscriber_id(subscriberId);

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
                    ps.setBoolean(6, true);
                }
            },

            new SqlOperation() {
                @Override
                public String getSql() {
                    return "INSERT INTO SUBSCRIBER (subscriber_id, name, address, phone, user_id) VALUES (?, ?, ?, ?, ?)";
                }

                @Override
                public void setParameters(PreparedStatement ps) throws Exception {
                    ps.setInt(1, subscriberId);
                    ps.setString(2, obj.getFullName());
                    ps.setString(3, obj.getAddress());
                    ps.setString(4, obj.getPhone());
                    ps.setInt(5, userId);
                }
            }
        };

        DbOperations.executeTransaction(operations, "Đăng ký thành công!");
    }

    public static void update(Subscriber obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static Subscriber getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static List<Subscriber> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}
