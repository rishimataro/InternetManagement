/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Plan;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import DAO.DbOperations.SqlOperation;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author hoang
 */
public class PlanDAO {
    public static void insert(final Plan obj){
        final int planId = DbOperations.getNextId("PLAN","plan_id");
        
        obj.setPlan_id(planId);
        
        SqlOperation[] operations = new SqlOperation[]{
            new SqlOperation() {
                @Override
                public String getSql(){
                    return "INSERT INTO PLAN(plan_id, name, price, max_speed_domestic, min_speed_domestinc, max_speed_international, min_speed_international) VALUES (?, ?, ?, ?, ?, ?, ?)";
                }
                @Override
                public void setParameters(PreparedStatement ps) throws Exception{
                    ps.setInt(1, planId);
                    ps.setString(2, obj.getName());
                    ps.setDouble(3, obj.getPrice());
                    ps.setDouble(4, obj.getMax_speed_domestic());
                    ps.setDouble(5, obj.getMin_speed_domestic());
                    ps.setDouble(6, obj.getMax_speed_international());
                    ps.setDouble(7, obj.getMin_speed_international());
                }
            }  
        };
        
        DbOperations.executeTransaction(operations, "Thêm gói cước thành công!");

    }
    public static void update(final Plan obj) {
        SqlOperation[] operations = new SqlOperation[]{
            new SqlOperation(){
                @Override
                public String getSql(){
                    return "UPDATE PLAN SET name = ?, price = ?, max_speed_domestic = ?, min_speed_domestic = ?, max_speed_international = ?, min_speed_international = ? WHERE plan_id = ?";
                }
                @Override
                public void setParameters(PreparedStatement ps) throws Exception{
                    ps.setString(1, obj.getName());
                    ps.setDouble(2, obj.getPrice());
                    ps.setDouble(3, obj.getMax_speed_domestic());
                    ps.setDouble(4, obj.getMin_speed_domestic());
                    ps.setDouble(5, obj.getMax_speed_international());
                    ps.setDouble(6, obj.getMin_speed_international());
                    ps.setInt(7, obj.getPlan_id());
                }
            }
        };
        DbOperations.executeTransaction(operations, "Cập nhật gói cước thành công!");
    }

    public static void delete(int id) {
        SqlOperation[] operations = new SqlOperation[]{
            new SqlOperation(){
                @Override
                public String getSql(){
                    return "DELETE FROM PLAN WHERE plan_id = ?";
                }
                @Override 
                public void setParameters(PreparedStatement ps) throws Exception {
                    ps.setInt(1, id);
                }
            }
        };
        DbOperations.executeTransaction(operations, "Xoá gói cước thành công!");
    }

    public static Plan getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static List<Plan> getAll() throws SQLException {
        // String query = "SELECT * FROM PLAN";
        // ResultSet rs = DbOperations.getData(query);
        // List<Plan> plans = new ArrayList<>();
        // try{
        //     while(rs.next()){
        //         Plan plan = new Plan();
        //         plan.setPlan_id(rs.getInt("plan_id"));
        //         plan.setName(rs.getString("name"));
        //         plan.setPrice(rs.getLong("price"));
        //         plan.setMax_speed_domestic(rs.getDouble("max_speed_domestic"));
        //         plan.setMin_speed_domestic(rs.getDouble("min_speed_domestic"));
        //         plan.setMax_speed_international(rs.getDouble("max_speed_international"));
        //         plan.setMin_speed_international(rs.getDouble("min_speed_international"));
        //     }
            
        // }catch (Exception e){
        //         JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        //     }
        // return plans;
        List<Plan> list = new ArrayList<>();
    Connection conn = DBConnection.getConnection(); // đảm bảo hàm này đúng
    String sql = "SELECT * FROM Plans";
    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery(sql);
    
    while (rs.next()) {
        Plan plan = new Plan();
        plan.setPlan_id(rs.getInt("Plan_id"));
        plan.setName(rs.getString("Name"));
        plan.setPrice(rs.getDouble("Price")); // hoặc float, double
        plan.setMin_speed_domestic(rs.getInt("Min_speed_domestic"));
        plan.setMax_speed_domestic(rs.getInt("Max_speed_domestic"));17
        plan.setMin_speed_international(rs.getInt("Min_speed_international"));
        plan.setMax_speed_international(rs.getInt("Max_speed_international"));
        list.add(plan);
    }
    
    rs.close();
    stmt.close();
    conn.close();
    return list;
    }
}
