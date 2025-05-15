/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Data Access Object for Customer management
 * @author ADMIN
 */
public class CustomerDAO {
    
    /**
     * Get all customers from the database
     * @return List of all customers
     */
    public static List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConnectionProvider.getConn();
            stmt = conn.createStatement();
            String query = "SELECT s.*, u.* FROM SUBSCRIBER s JOIN USER u ON s.user_id = u.user_id";
            rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setSubscriber_id(rs.getInt("subscriber_id"));
                customer.setFullName(rs.getString("name"));
                customer.setAddress(rs.getString("address"));
                customer.setPhone(rs.getString("phone"));
                customer.setUser_id(rs.getInt("user_id"));
                customer.setUsername(rs.getString("username"));
                customer.setPassword(rs.getString("password"));
                customer.setRole(rs.getString("role"));
                customer.setIsActive(rs.getBoolean("isActive"));
                
                if (rs.getTimestamp("created_at") != null) {
                    customer.setCreate_at(rs.getTimestamp("created_at").toLocalDateTime());
                }
                
                customers.add(customer);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error retrieving customers: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return customers;
    }
    
    /**
     * Get a customer by ID
     * @param id The customer ID
     * @return The customer object or null if not found
     */
    public static Customer getCustomerById(int id) {
        Customer customer = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConnectionProvider.getConn();
            String query = "SELECT s.*, u.* FROM SUBSCRIBER s JOIN USER u ON s.user_id = u.user_id WHERE s.subscriber_id = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                customer = new Customer();
                customer.setSubscriber_id(rs.getInt("subscriber_id"));
                customer.setFullName(rs.getString("name"));
                customer.setAddress(rs.getString("address"));
                customer.setPhone(rs.getString("phone"));
                customer.setUser_id(rs.getInt("user_id"));
                customer.setUsername(rs.getString("username"));
                customer.setPassword(rs.getString("password"));
                customer.setRole(rs.getString("role"));
                customer.setIsActive(rs.getBoolean("isActive"));
                
                if (rs.getTimestamp("created_at") != null) {
                    customer.setCreate_at(rs.getTimestamp("created_at").toLocalDateTime());
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error retrieving customer: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return customer;
    }
    
    /**
     * Search for customers based on criteria
     * @param name Customer name (can be partial)
     * @param phone Customer phone number (can be partial)
     * @return List of matching customers
     */
    public static List<Customer> searchCustomers(String name, String phone) {
        List<Customer> customers = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConnectionProvider.getConn();
            
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("SELECT s.*, u.* FROM SUBSCRIBER s JOIN USER u ON s.user_id = u.user_id WHERE 1=1");
            
            if (name != null && !name.trim().isEmpty()) {
                queryBuilder.append(" AND s.name LIKE ?");
            }
            
            if (phone != null && !phone.trim().isEmpty()) {
                queryBuilder.append(" AND s.phone LIKE ?");
            }
            
            stmt = conn.prepareStatement(queryBuilder.toString());
            
            int paramIndex = 1;
            if (name != null && !name.trim().isEmpty()) {
                stmt.setString(paramIndex++, "%" + name.trim() + "%");
            }
            
            if (phone != null && !phone.trim().isEmpty()) {
                stmt.setString(paramIndex, "%" + phone.trim() + "%");
            }
            
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setSubscriber_id(rs.getInt("subscriber_id"));
                customer.setFullName(rs.getString("name"));
                customer.setAddress(rs.getString("address"));
                customer.setPhone(rs.getString("phone"));
                customer.setUser_id(rs.getInt("user_id"));
                customer.setUsername(rs.getString("username"));
                customer.setPassword(rs.getString("password"));
                customer.setRole(rs.getString("role"));
                customer.setIsActive(rs.getBoolean("isActive"));
                
                if (rs.getTimestamp("created_at") != null) {
                    customer.setCreate_at(rs.getTimestamp("created_at").toLocalDateTime());
                }
                
                customers.add(customer);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error searching customers: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return customers;
    }
    
    /**
     * Add a new customer
     * @param customer The customer to add
     * @return true if successful, false otherwise
     */
    public static boolean addCustomer(final Customer customer) {
        try {
            final int userId = DbOperations.getNextId("USER", "user_id");
            final int subscriberId = DbOperations.getNextId("SUBSCRIBER", "subscriber_id");
            
            customer.setUser_id(userId);
            customer.setSubscriber_id(subscriberId);
            
            DbOperations.SqlOperation[] operations = new DbOperations.SqlOperation[] {
                new DbOperations.SqlOperation() {
                    @Override
                    public String getSql() {
                        return "INSERT INTO USER(user_id, username, password, role, created_at, isActive) VALUES (?, ?, ?, ?, ?, ?)";
                    }

                    @Override
                    public void setParameters(PreparedStatement ps) throws Exception {
                        ps.setInt(1, userId);
                        ps.setString(2, customer.getUsername());
                        ps.setString(3, customer.getPassword());
                        ps.setString(4, customer.getRole());
                        ps.setTimestamp(5, Timestamp.valueOf(customer.getCreate_at() != null ? customer.getCreate_at() : LocalDateTime.now()));
                        ps.setBoolean(6, customer.isIsActive());
                    }
                },

                new DbOperations.SqlOperation() {
                    @Override
                    public String getSql() {
                        return "INSERT INTO SUBSCRIBER (subscriber_id, name, address, phone, user_id) VALUES (?, ?, ?, ?, ?)";
                    }

                    @Override
                    public void setParameters(PreparedStatement ps) throws Exception {
                        ps.setInt(1, subscriberId);
                        ps.setString(2, customer.getFullName());
                        ps.setString(3, customer.getAddress());
                        ps.setString(4, customer.getPhone());
                        ps.setInt(5, userId);
                    }
                }
            };
            
            DbOperations.executeTransaction(operations, "Thêm khách hàng thành công!");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error adding customer: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    /**
     * Update an existing customer
     * @param customer The customer to update
     * @return true if successful, false otherwise
     */
    public static boolean updateCustomer(final Customer customer) {
        try {
            DbOperations.SqlOperation[] operations = new DbOperations.SqlOperation[] {
                new DbOperations.SqlOperation() {
                    @Override
                    public String getSql() {
                        return "UPDATE USER SET username = ?, password = ?, role = ?, isActive = ? WHERE user_id = ?";
                    }

                    @Override
                    public void setParameters(PreparedStatement ps) throws Exception {
                        ps.setString(1, customer.getUsername());
                        ps.setString(2, customer.getPassword());
                        ps.setString(3, customer.getRole());
                        ps.setBoolean(4, customer.isIsActive());
                        ps.setInt(5, customer.getUser_id());
                    }
                },

                new DbOperations.SqlOperation() {
                    @Override
                    public String getSql() {
                        return "UPDATE SUBSCRIBER SET name = ?, address = ?, phone = ? WHERE subscriber_id = ?";
                    }

                    @Override
                    public void setParameters(PreparedStatement ps) throws Exception {
                        ps.setString(1, customer.getFullName());
                        ps.setString(2, customer.getAddress());
                        ps.setString(3, customer.getPhone());
                        ps.setInt(4, customer.getSubscriber_id());
                    }
                }
            };
            
            DbOperations.executeTransaction(operations, "Cập nhật khách hàng thành công!");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error updating customer: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    /**
     * Delete a customer
     * @param customerId The ID of the customer to delete
     * @return true if successful, false otherwise
     */
    public static boolean deleteCustomer(final int customerId) {
        try {
            // First get the customer to get the user_id
            Customer customer = getCustomerById(customerId);
            if (customer == null) {
                return false;
            }
            
            final int userId = customer.getUser_id();
            
            DbOperations.SqlOperation[] operations = new DbOperations.SqlOperation[] {
                new DbOperations.SqlOperation() {
                    @Override
                    public String getSql() {
                        return "DELETE FROM SUBSCRIBER WHERE subscriber_id = ?";
                    }

                    @Override
                    public void setParameters(PreparedStatement ps) throws Exception {
                        ps.setInt(1, customerId);
                    }
                },

                new DbOperations.SqlOperation() {
                    @Override
                    public String getSql() {
                        return "DELETE FROM USER WHERE user_id = ?";
                    }

                    @Override
                    public void setParameters(PreparedStatement ps) throws Exception {
                        ps.setInt(1, userId);
                    }
                }
            };
            
            DbOperations.executeTransaction(operations, "Xóa khách hàng thành công!");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error deleting customer: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
