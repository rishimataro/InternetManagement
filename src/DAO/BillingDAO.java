/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Billing;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Data Access Object for Billing management
 * @author ADMIN
 */
public class BillingDAO {

    /**
     * Get all billing records from the database
     * @return List of all billing records
     */
    public static List<Billing> getAllBillings() {
        List<Billing> billings = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionProvider.getConn();
            stmt = conn.createStatement();
            String query = "SELECT * FROM BILL_HISTORY";
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                Billing billing = new Billing();
                billing.setBillingId(rs.getInt("bill_id"));
                billing.setContractId(rs.getInt("contract_id"));
                billing.setBillingPeriod(rs.getDate("billing_period").toLocalDate());
                billing.setAmount(rs.getDouble("amount"));
                billing.setStatus(rs.getString("status"));

                java.sql.Timestamp paymentTimestamp = rs.getTimestamp("payment_date");
                if (paymentTimestamp != null) {
                    billing.setPaymentDate(paymentTimestamp.toLocalDateTime());
                }

                billings.add(billing);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error retrieving billings: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return billings;
    }

    /**
     * Get a billing record by ID
     * @param id The billing ID
     * @return The billing object or null if not found
     */
    public static Billing getBillingById(int id) {
        Billing billing = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionProvider.getConn();
            String query = "SELECT * FROM BILL_HISTORY WHERE bill_id = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                billing = new Billing();
                billing.setBillingId(rs.getInt("bill_id"));
                billing.setContractId(rs.getInt("contract_id"));
                billing.setBillingPeriod(rs.getDate("billing_period").toLocalDate());
                billing.setAmount(rs.getDouble("amount"));
                billing.setStatus(rs.getString("status"));

                java.sql.Timestamp paymentTimestamp = rs.getTimestamp("payment_date");
                if (paymentTimestamp != null) {
                    billing.setPaymentDate(paymentTimestamp.toLocalDateTime());
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error retrieving billing: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return billing;
    }

    /**
     * Get billing records by contract ID
     * @param contractId The contract ID
     * @return List of billing records for the contract
     */
    public static List<Billing> getBillingsByContractId(int contractId) {
        List<Billing> billings = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionProvider.getConn();
            String query = "SELECT * FROM BILL_HISTORY WHERE contract_id = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, contractId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Billing billing = new Billing();
                billing.setBillingId(rs.getInt("bill_id"));
                billing.setContractId(rs.getInt("contract_id"));
                billing.setBillingPeriod(rs.getDate("billing_period").toLocalDate());
                billing.setAmount(rs.getDouble("amount"));
                billing.setStatus(rs.getString("status"));

                java.sql.Timestamp paymentTimestamp = rs.getTimestamp("payment_date");
                if (paymentTimestamp != null) {
                    billing.setPaymentDate(paymentTimestamp.toLocalDateTime());
                }

                billings.add(billing);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error retrieving billings: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return billings;
    }

    /**
     * Search for billing records based on criteria
     * @param billingId Billing ID (0 to ignore)
     * @param contractId Contract ID (0 to ignore)
     * @param paymentMethod Payment method (null to ignore)
     * @param status Status (null to ignore)
     * @return List of matching billing records
     */
    public static List<Billing> searchBillings(int billingId, int contractId, String paymentMethod, String status) {
        List<Billing> billings = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionProvider.getConn();

            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("SELECT * FROM BILL_HISTORY WHERE 1=1");

            if (billingId > 0) {
                queryBuilder.append(" AND bill_id = ?");
            }

            if (contractId > 0) {
                queryBuilder.append(" AND contract_id = ?");
            }

            if (paymentMethod != null && !paymentMethod.trim().isEmpty()) {
                queryBuilder.append(" AND payment_method = ?");
            }

            if (status != null && !status.trim().isEmpty()) {
                queryBuilder.append(" AND status = ?");
            }

            stmt = conn.prepareStatement(queryBuilder.toString());

            int paramIndex = 1;
            if (billingId > 0) {
                stmt.setInt(paramIndex++, billingId);
            }

            if (contractId > 0) {
                stmt.setInt(paramIndex++, contractId);
            }

            if (paymentMethod != null && !paymentMethod.trim().isEmpty()) {
                stmt.setString(paramIndex++, paymentMethod);
            }

            if (status != null && !status.trim().isEmpty()) {
                stmt.setString(paramIndex, status);
            }

            rs = stmt.executeQuery();

            while (rs.next()) {
                Billing billing = new Billing();
                billing.setBillingId(rs.getInt("bill_id"));
                billing.setContractId(rs.getInt("contract_id"));
                billing.setBillingPeriod(rs.getDate("billing_period").toLocalDate());
                billing.setAmount(rs.getDouble("amount"));
                billing.setStatus(rs.getString("status"));

                java.sql.Timestamp paymentTimestamp = rs.getTimestamp("payment_date");
                if (paymentTimestamp != null) {
                    billing.setPaymentDate(paymentTimestamp.toLocalDateTime());
                }

                billings.add(billing);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error searching billings: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return billings;
    }

    /**
     * Add a new billing record
     * @param billing The billing record to add
     * @return true if successful, false otherwise
     */
    public static boolean addBilling(final Billing billing) {
        try {
            final int billingId = DbOperations.getNextId("BILL_HISTORY", "bill_id");
            billing.setBillingId(billingId);

            DbOperations.SqlOperation[] operations = new DbOperations.SqlOperation[] {
                new DbOperations.SqlOperation() {
                    @Override
                    public String getSql() {
                        return "INSERT INTO BILL_HISTORY (bill_id, contract_id, billing_period, amount, status, payment_date) VALUES (?, ?, ?, ?, ?, ?)";
                    }

                    @Override
                    public void setParameters(PreparedStatement ps) throws Exception {
                        ps.setInt(1, billingId);
                        ps.setInt(2, billing.getContractId());

                        if (billing.getBillingPeriod() != null) {
                            ps.setDate(3, Date.valueOf(billing.getBillingPeriod()));
                        } else {
                            ps.setNull(3, java.sql.Types.DATE);
                        }

                        ps.setDouble(4, billing.getAmount());
                        ps.setString(5, billing.getStatus());

                        if (billing.getPaymentDate() != null) {
                            ps.setTimestamp(6, java.sql.Timestamp.valueOf(billing.getPaymentDate()));
                        } else {
                            ps.setNull(6, java.sql.Types.TIMESTAMP);
                        }
                    }
                }
            };

            DbOperations.executeTransaction(operations, "Thêm hóa đơn thành công!");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error adding billing: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Update an existing billing record
     * @param billing The billing record to update
     * @return true if successful, false otherwise
     */
    public static boolean updateBilling(final Billing billing) {
        try {
            DbOperations.SqlOperation[] operations = new DbOperations.SqlOperation[] {
                new DbOperations.SqlOperation() {
                    @Override
                    public String getSql() {
                        return "UPDATE BILL_HISTORY SET contract_id = ?, billing_period = ?, amount = ?, status = ?, payment_date = ? WHERE bill_id = ?";
                    }

                    @Override
                    public void setParameters(PreparedStatement ps) throws Exception {
                        ps.setInt(1, billing.getContractId());

                        if (billing.getBillingPeriod() != null) {
                            ps.setDate(2, Date.valueOf(billing.getBillingPeriod()));
                        } else {
                            ps.setNull(2, java.sql.Types.DATE);
                        }

                        ps.setDouble(3, billing.getAmount());
                        ps.setString(4, billing.getStatus());

                        if (billing.getPaymentDate() != null) {
                            ps.setTimestamp(5, java.sql.Timestamp.valueOf(billing.getPaymentDate()));
                        } else {
                            ps.setNull(5, java.sql.Types.TIMESTAMP);
                        }

                        ps.setInt(6, billing.getBillingId());
                    }
                }
            };

            DbOperations.executeTransaction(operations, "Cập nhật hóa đơn thành công!");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error updating billing: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Delete a billing record
     * @param billingId The ID of the billing record to delete
     * @return true if successful, false otherwise
     */
    public static boolean deleteBilling(final int billingId) {
        try {
            DbOperations.SqlOperation[] operations = new DbOperations.SqlOperation[] {
                new DbOperations.SqlOperation() {
                    @Override
                    public String getSql() {
                        return "DELETE FROM BILL_HISTORY WHERE bill_id = ?";
                    }

                    @Override
                    public void setParameters(PreparedStatement ps) throws Exception {
                        ps.setInt(1, billingId);
                    }
                }
            };

            DbOperations.executeTransaction(operations, "Xóa hóa đơn thành công!");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error deleting billing: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Update the status of a billing record
     * @param billingId The ID of the billing record
     * @param status The new status
     * @return true if successful, false otherwise
     */
    public static boolean updateBillingStatus(final int billingId, final String status) {
        try {
            DbOperations.SqlOperation[] operations = new DbOperations.SqlOperation[] {
                new DbOperations.SqlOperation() {
                    @Override
                    public String getSql() {
                        return "UPDATE BILL_HISTORY SET status = ? WHERE bill_id = ?";
                    }

                    @Override
                    public void setParameters(PreparedStatement ps) throws Exception {
                        ps.setString(1, status);
                        ps.setInt(2, billingId);
                    }
                }
            };

            DbOperations.executeTransaction(operations, "Cập nhật trạng thái hóa đơn thành công!");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error updating billing status: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}