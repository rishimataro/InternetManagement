/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package internet.mangement.system;

import DAO.ConnectionProvider;
import DAO.CustomerDAO;
import Model.Customer;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Test class for CustomerManagementForm
 * @author ADMIN
 */
public class CustomerManagementTest {
    
    /**
     * Test database connection
     * @return true if connection is successful, false otherwise
     */
    public static boolean testConnection() {
        Connection conn = ConnectionProvider.getConn();
        boolean success = (conn != null);
        
        if (success) {
            System.out.println("Database connection successful!");
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Database connection failed!");
        }
        
        return success;
    }
    
    /**
     * Test retrieving all customers
     */
    public static void testGetAllCustomers() {
        System.out.println("Testing getAllCustomers()...");
        List<Customer> customers = CustomerDAO.getAllCustomers();
        
        if (customers != null && !customers.isEmpty()) {
            System.out.println("Successfully retrieved " + customers.size() + " customers:");
            for (Customer customer : customers) {
                System.out.println(customer);
            }
        } else {
            System.out.println("No customers found or error retrieving customers.");
        }
    }
    
    /**
     * Test searching for customers
     */
    public static void testSearchCustomers() {
        System.out.println("Testing searchCustomers()...");
        
        // Test search by name
        String searchName = "a"; // This should match some customers
        List<Customer> customersByName = CustomerDAO.searchCustomers(searchName, "");
        
        if (customersByName != null && !customersByName.isEmpty()) {
            System.out.println("Successfully found " + customersByName.size() + " customers with name containing '" + searchName + "':");
            for (Customer customer : customersByName) {
                System.out.println(customer);
            }
        } else {
            System.out.println("No customers found with name containing '" + searchName + "'.");
        }
        
        // Test search by phone
        String searchPhone = "0"; // This should match some customers
        List<Customer> customersByPhone = CustomerDAO.searchCustomers("", searchPhone);
        
        if (customersByPhone != null && !customersByPhone.isEmpty()) {
            System.out.println("Successfully found " + customersByPhone.size() + " customers with phone containing '" + searchPhone + "':");
            for (Customer customer : customersByPhone) {
                System.out.println(customer);
            }
        } else {
            System.out.println("No customers found with phone containing '" + searchPhone + "'.");
        }
    }
    
    /**
     * Test adding a new customer
     */
    public static void testAddCustomer() {
        System.out.println("Testing addCustomer()...");
        
        Customer newCustomer = new Customer();
        newCustomer.setFullName("Test Customer");
        newCustomer.setAddress("Test Address");
        newCustomer.setPhone("0123456789");
        newCustomer.setUsername("testuser");
        newCustomer.setPassword("password123");
        newCustomer.setRole("user");
        newCustomer.setCreate_at(LocalDateTime.now());
        newCustomer.setIsActive(true);
        
        boolean success = CustomerDAO.addCustomer(newCustomer);
        
        if (success) {
            System.out.println("Successfully added new customer with ID: " + newCustomer.getSubscriber_id());
        } else {
            System.out.println("Failed to add new customer.");
        }
    }
    
    /**
     * Test updating a customer
     */
    public static void testUpdateCustomer() {
        System.out.println("Testing updateCustomer()...");
        
        // First get all customers to find one to update
        List<Customer> customers = CustomerDAO.getAllCustomers();
        
        if (customers != null && !customers.isEmpty()) {
            // Get the first customer
            Customer customerToUpdate = customers.get(0);
            
            // Update some fields
            customerToUpdate.setFullName(customerToUpdate.getFullName() + " (Updated)");
            customerToUpdate.setAddress(customerToUpdate.getAddress() + " (Updated)");
            
            boolean success = CustomerDAO.updateCustomer(customerToUpdate);
            
            if (success) {
                System.out.println("Successfully updated customer with ID: " + customerToUpdate.getSubscriber_id());
            } else {
                System.out.println("Failed to update customer.");
            }
        } else {
            System.out.println("No customers found to update.");
        }
    }
    
    /**
     * Run all tests
     */
    public static void runAllTests() {
        if (testConnection()) {
            testGetAllCustomers();
            testSearchCustomers();
            // Uncomment these to actually modify the database
            // testAddCustomer();
            // testUpdateCustomer();
        }
    }
    
    /**
     * Main method
     * @param args command line arguments
     */
    public static void main(String[] args) {
        runAllTests();
        
        // Launch the CustomerManagementForm
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CustomerManagementForm().setVisible(true);
            }
        });
    }
}
