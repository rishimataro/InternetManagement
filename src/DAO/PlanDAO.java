/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Plan;

import java.sql.*;
import java.util.List;

import DAO.DbOperations.SqlOperation;

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
                    return "INSERT INTO PLAN(plan_id, name, price, max_speed_domestic, min_speed_domestic, max_speed_international, min_speed_international) VALUES (?, ?, ?, ?, ?, ?, ?)";
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

    public static boolean delete(int id) {
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
        return DbOperations.executeTransaction(operations, "Xoá gói cước thành công!");
    }

    public static Plan getById(int id) throws SQLException {
        String sql = "SELECT * FROM PLAN WHERE plan_id = ?";
        Connection conn = ConnectionProvider.getConn();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new Plan(
                    rs.getInt("plan_id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("min_speed_domestic"),
                    rs.getInt("max_speed_domestic"),
                    rs.getInt("min_speed_international"),
                    rs.getInt("max_speed_international")
            );
        }
        return null;
    }
    public static List<Plan> searchPlans(String keyword,
                                     Double priceMin, Double priceMax,
                                     Integer speedMinDomestic, Integer speedMaxDomestic,
                                     Integer speedMinIntl, Integer speedMaxIntl) throws SQLException {
    List<Plan> result = new ArrayList<>();
    String sql = "SELECT * FROM PLAN WHERE 1=1";

    if (keyword != null && !keyword.isEmpty()) {
        sql += " AND (plan_id LIKE ? OR name LIKE ?)";
    }
    if (priceMin != null) sql += " AND price >= ?";
    if (priceMax != null) sql += " AND price <= ?";
    if (speedMinDomestic != null) sql += " AND min_speed_domestic >= ?";
    if (speedMaxDomestic != null) sql += " AND max_speed_domestic <= ?";
    if (speedMinIntl != null) sql += " AND min_speed_international >= ?";
    if (speedMaxIntl != null) sql += " AND max_speed_international <= ?";

    Connection conn = ConnectionProvider.getConn();
    PreparedStatement stmt = conn.prepareStatement(sql);

    int index = 1;
    if (keyword != null && !keyword.isEmpty()) {
        stmt.setString(index++, "%" + keyword + "%");
        stmt.setString(index++, "%" + keyword + "%");
    }
    if (priceMin != null) stmt.setDouble(index++, priceMin);
    if (priceMax != null) stmt.setDouble(index++, priceMax);
    if (speedMinDomestic != null) stmt.setInt(index++, speedMinDomestic);
    if (speedMaxDomestic != null) stmt.setInt(index++, speedMaxDomestic);
    if (speedMinIntl != null) stmt.setInt(index++, speedMinIntl);
    if (speedMaxIntl != null) stmt.setInt(index++, speedMaxIntl);

    ResultSet rs = stmt.executeQuery();
    while (rs.next()) {
        Plan p = new Plan(
            rs.getInt("plan_id"),
            rs.getString("name"),
            rs.getDouble("price"),
            rs.getInt("min_speed_domestic"),
            rs.getInt("max_speed_domestic"),
            rs.getInt("min_speed_international"),
            rs.getInt("max_speed_international")
        );
        result.add(p);
    }

    return result;
}


    public static List<Plan> getAll() throws SQLException {

        List<Plan> list = new ArrayList<>();
        Connection conn = ConnectionProvider.getConn(); // đảm bảo hàm này đúng
        String sql = "SELECT * FROM PLAN";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            Plan plan = new Plan();
            plan.setPlan_id(rs.getInt("Plan_id"));
            plan.setName(rs.getString("Name"));
            plan.setPrice(rs.getDouble("price")); // hoặc float, double
            plan.setMin_speed_domestic(rs.getDouble("Min_speed_domestic"));
            plan.setMax_speed_domestic(rs.getDouble("Max_speed_domestic"));
            plan.setMin_speed_international(rs.getDouble("Min_speed_international"));
            plan.setMax_speed_international(rs.getDouble("Max_speed_international"));
            list.add(plan);
        }

        rs.close();
        stmt.close();
        conn.close();
        return list;
        }
}
