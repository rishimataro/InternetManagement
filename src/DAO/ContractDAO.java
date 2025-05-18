package DAO;

import Model.Contract;

import java.sql.*;
import DAO.DbOperations.SqlOperation;
import Model.Plan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ContractDAO {
    public static List<Contract> getAll() throws SQLException{
        List<Contract> contracts = new ArrayList<>();
        Connection conn = ConnectionProvider.getConn();
        String sql = "SELECT * FROM CONTRACT";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            Contract contract = new Contract();
            contract.setContract_id(rs.getInt("contract_id"));
            contract.setSub_id(rs.getInt("subscriber_id"));
            contract.setPackage_id(rs.getInt("plan_id"));
            contract.setDate_sign(rs.getDate("date_sign").toLocalDate().atStartOfDay());
            contract.setDate_expiration(rs.getDate("date_expiration").toLocalDate().atStartOfDay());
            contract.setAddress(rs.getString("address"));
            contract.setCreate_at(rs.getDate("created_at").toLocalDate().atStartOfDay());
            contracts.add(contract);
        }
        rs.close();
        stmt.close();
        conn.close();
        return contracts;
    }
    public static List<Contract> searchContracts(String subscriberName, Integer Plan_id,
                                         LocalDateTime dateSignFrom, LocalDateTime dateSignTo, LocalDateTime dateExpireFrom, LocalDateTime dateExpireTo) throws SQLException {
        List<Contract> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder("""
        SELECT c.*
        FROM CONTRACT c
        JOIN SUBSCRIBER s ON c.subscriber_id = s.subscriber_id
        JOIN PLAN p ON c.plan_id = p.plan_id
        WHERE 1=1
        """);

        List<Object> params = new ArrayList<>();
        if (subscriberName != null && !subscriberName.isBlank()) {
            sql.append(" AND s.name LIKE ?");
            params.add("%" + subscriberName + "%");
        }
        if (Plan_id != null) {
            sql.append(" AND p.plan_id = ?");
            params.add(Plan_id);
        }
        if (dateSignFrom != null && dateSignTo != null) {
            sql.append(" AND c.date_sign BETWEEN ? AND ?");
            params.add(dateSignFrom);
            params.add(dateSignTo);
        }
        if (dateExpireFrom != null && dateExpireTo != null) {
            sql.append(" AND c.date_expiration BETWEEN ? AND ?");
                    params.add(dateExpireFrom);
                    params.add(dateExpireTo);
        }

        Connection conn = ConnectionProvider.getConn();
        PreparedStatement stmt = conn.prepareStatement(sql.toString());
        for(int i = 0; i < params.size(); i++) {
            stmt.setObject(i + 1, params.get(i));
        }


        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Contract contract = new Contract();
            contract.setContract_id(rs.getInt("contract_id"));
            contract.setSub_id(rs.getInt("subscriber_id"));
            contract.setPackage_id(rs.getInt("plan_id"));
            contract.setDate_sign(rs.getDate("date_sign").toLocalDate().atStartOfDay());
            contract.setDate_expiration(rs.getDate("date_expiration").toLocalDate().atStartOfDay());
            contract.setAddress(rs.getString("address"));
            contract.setCreate_at(rs.getDate("created_at").toLocalDate().atStartOfDay());
            result.add(contract);
        }
        rs.close();
        stmt.close();
        conn.close();
        return result;
    }
    public static boolean addContract(final Contract obj){
        final int contract_id = DbOperations.getNextId("CONTRACT", "contract_id");
        obj.setContract_id(contract_id);

        SqlOperation[] operations = new SqlOperation[] {
            new SqlOperation() {
                @Override
                public String getSql(){
                    return "INSERT INTO CONTRACT(contract_id, subscriber_id, plan_id, date_sign, date_expiration, address, created_at) VALUES (?, ?, ?, ?, ?, ?, ?)";
                }
                @Override
                public void setParameters(PreparedStatement ps) throws Exception{
                    ps.setInt(1, contract_id);
                    ps.setInt(2, obj.getSub_id());
                    ps.setInt(3, obj.getPackage_id());
                    ps.setDate(4, Date.valueOf(obj.getDate_sign().toLocalDate()));
                    ps.setDate(5, Date.valueOf(obj.getDate_expiration().toLocalDate()));
                    ps.setString(6, obj.getAddress());
                    ps.setDate(7, Date.valueOf(obj.getCreate_at().toLocalDate()));
                }
        }

        };
        return DbOperations.executeTransaction(operations, "Tạo hợp đồng thành công!");
    }
    public static boolean updateContract(final Contract obj){
        SqlOperation[] operations = new SqlOperation[] {
            new SqlOperation() {
                @Override
                public String getSql(){
                    return "UPDATE CONTRACT SET subscriber_id = ?, plan_id = ?, date_sign = ?, date_expiration = ?, address = ?, created_at = ? WHERE contract_id = ?";
                }
                @Override
                public void setParameters(PreparedStatement ps) throws Exception{
                    ps.setInt(1, obj.getSub_id());
                    ps.setInt(2, obj.getPackage_id());
                    ps.setDate(3, Date.valueOf(obj.getDate_sign().toLocalDate()));
                    ps.setDate(4, Date.valueOf(obj.getDate_expiration().toLocalDate()));
                    ps.setString(5, obj.getAddress());
                    ps.setDate(6, Date.valueOf(obj.getCreate_at().toLocalDate()));
                    ps.setInt(7, obj.getContract_id());
                }
            }
        };
        return DbOperations.executeTransaction(operations, "Cập nhật thành công!");
    }
    public static boolean deleteContract(int id){
        SqlOperation[] operations = new SqlOperation[] {
            new SqlOperation() {
                @Override
                public String getSql(){
                    return "DELETE FROM CONTRACT WHERE contract_id = ?";
                }
                @Override
                public void setParameters(PreparedStatement ps) throws Exception{
                    ps.setInt(1, id);
                }
            }
        };
        return DbOperations.executeTransaction(operations, "Xoá hợp đồng thành công!");
    }

    public static List<Contract> getBySubcriberID(int id) throws SQLException{
        List<Contract> contracts = new ArrayList<>();
        Connection conn = ConnectionProvider.getConn();
        String sql = "SELECT * FROM CONTRACT WHRE subscriber_id = ?" ;
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Contract contract = new Contract();
            contract.setContract_id(rs.getInt("contract_id"));
            contract.setSub_id(rs.getInt("subscriber_id"));
            contract.setPackage_id(rs.getInt("plan_id"));
            contract.setDate_sign(rs.getDate("date_sign").toLocalDate().atStartOfDay());
            contract.setDate_expiration(rs.getDate("date_expiration").toLocalDate().atStartOfDay());
            contract.setAddress(rs.getString("address"));
            contract.setCreate_at(rs.getDate("created_at").toLocalDate().atStartOfDay());
            contracts.add(contract);
        }
        rs.close();
        stmt.close();
        conn.close();
        return contracts;
    }
}
