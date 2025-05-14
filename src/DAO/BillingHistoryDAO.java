package DAO;

import Model.BillingHistory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class BillingHistoryDAO {
    private Connection connection;
    
    private void connectToDatabase() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/internet_management", "root", "");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database connection error: " + e.getMessage());
        }
    }
    
    public List<BillingHistory> getAllBillingRecords() {
        List<BillingHistory> billingRecords = new ArrayList<>();
        try {
            connectToDatabase();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM billing_history");
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                BillingHistory record = new BillingHistory();
                record.setContract_id(resultSet.getInt("contract_id"));
                
                java.sql.Date billingDate = resultSet.getDate("billing_period");
                if (billingDate != null) {
                    record.setBilling_period(billingDate.toLocalDate());
                }
                
                record.setAmount(resultSet.getDouble("amount"));
                
                Timestamp paymentTimestamp = resultSet.getTimestamp("payment_date");
                if (paymentTimestamp != null) {
                    record.setPayment_date(paymentTimestamp.toLocalDateTime());
                }
                
                record.setStatus(resultSet.getString("status"));
                billingRecords.add(record);
            }
            
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading data: " + e.getMessage());
        }
        return billingRecords;
    }
    
    public List<BillingHistory> searchBillingRecords(String contractId, String billingPeriod, String status) {
        List<BillingHistory> billingRecords = new ArrayList<>();
        try {
            connectToDatabase();
            StringBuilder query = new StringBuilder("SELECT * FROM billing_history WHERE 1=1");
            
            if (contractId != null && !contractId.isEmpty()) {
                query.append(" AND contract_id = ?");
            }
            
            if (billingPeriod != null && !billingPeriod.equals("Item 1")) {
                query.append(" AND billing_period = ?");
            }
            
            if (status != null && !status.equals("Item 1")) {
                query.append(" AND status = ?");
            }
            
            PreparedStatement statement = connection.prepareStatement(query.toString());
            
            int paramIndex = 1;
            if (contractId != null && !contractId.isEmpty()) {
                statement.setString(paramIndex++, contractId);
            }
            
            if (billingPeriod != null && !billingPeriod.equals("Item 1")) {
                statement.setString(paramIndex++, billingPeriod);
            }
            
            if (status != null && !status.equals("Item 1")) {
                statement.setString(paramIndex, status);
            }
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                BillingHistory record = new BillingHistory();
                record.setContract_id(Integer.parseInt(resultSet.getString("contract_id")));
                
                java.sql.Date billingDate = resultSet.getDate("billing_period");
                if (billingDate != null) {
                    record.setBilling_period(billingDate.toLocalDate());
                }
                
                record.setAmount(resultSet.getDouble("amount"));
                
                Timestamp paymentTimestamp = resultSet.getTimestamp("payment_date");
                if (paymentTimestamp != null) {
                    record.setPayment_date(paymentTimestamp.toLocalDateTime());
                }
                
                record.setStatus(resultSet.getString("status"));
                billingRecords.add(record);
            }
            
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Search error: " + e.getMessage());
        }
        return billingRecords;
    }
    
    public boolean addBillingRecord(BillingHistory record) {
        try {
            connectToDatabase();
            PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO billing_history (contract_id, billing_period, amount, payment_date, status) VALUES (?, ?, ?, ?, ?)"
            );
            statement.setInt(1, record.getContract_id());
            
            if (record.getBilling_period() != null) {
                statement.setDate(2, java.sql.Date.valueOf(record.getBilling_period()));
            } else {
                statement.setNull(2, java.sql.Types.DATE);
            }
            
            statement.setDouble(3, record.getAmount());
            
            if (record.getPayment_date() != null) {
                statement.setTimestamp(4, Timestamp.valueOf(record.getPayment_date()));
            } else {
                statement.setNull(4, java.sql.Types.TIMESTAMP);
            }
            
            statement.setString(5, record.getStatus());
            
            int result = statement.executeUpdate();
            connection.close();
            return result > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error adding record: " + e.getMessage());
            return false;
        }
    }
    
    public boolean updateBillingRecord(BillingHistory record) {
        try {
            connectToDatabase();
            PreparedStatement statement = connection.prepareStatement(
                "UPDATE billing_history SET amount = ?, payment_date = ?, status = ? WHERE contract_id = ? AND billing_period = ?"
            );
            
            statement.setDouble(1, record.getAmount());
            
            if (record.getPayment_date() != null) {
                statement.setTimestamp(2, Timestamp.valueOf(record.getPayment_date()));
            } else {
                statement.setNull(2, java.sql.Types.TIMESTAMP);
            }
            
            statement.setString(3, record.getStatus());
            statement.setInt(4, record.getContract_id());
            
            if (record.getBilling_period() != null) {
                statement.setDate(5, java.sql.Date.valueOf(record.getBilling_period()));
            } else {
                statement.setNull(5, java.sql.Types.DATE);
            }
            
            int result = statement.executeUpdate();
            connection.close();
            return result > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error updating record: " + e.getMessage());
            return false;
        }
    }
    
    public boolean updateBillingStatus(String contractId, String billingPeriod, String status) {
        try {
            connectToDatabase();
            PreparedStatement statement = connection.prepareStatement(
                "UPDATE billing_history SET status = ? WHERE contract_id = ? AND billing_period = ?"
            );
            statement.setString(1, status);
            statement.setString(2, contractId);
            statement.setString(3, billingPeriod);
            
            int result = statement.executeUpdate();
            connection.close();
            return result > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error updating status: " + e.getMessage());
            return false;
        }
    }
    
    public boolean deleteBillingRecord(String contractId, String billingPeriod) {
        try {
            connectToDatabase();
            PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM billing_history WHERE contract_id = ? AND billing_period = ?"
            );
            
            statement.setString(1, contractId);
            statement.setString(2, billingPeriod);
            
            int result = statement.executeUpdate();
            connection.close();
            return result > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error deleting record: " + e.getMessage());
            return false;
        }
    }
}




