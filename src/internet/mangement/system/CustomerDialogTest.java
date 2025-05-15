/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package internet.mangement.system;

import DAO.CustomerDAO;
import Model.Customer;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * Test class for CustomerDialog
 * @author ADMIN
 */
public class CustomerDialogTest {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Customer Dialog Test");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 300);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                
                // Test adding a new customer
                testAddCustomer(frame);
                
                // Test editing an existing customer
                // Uncomment this to test editing
                // testEditCustomer(frame);
            }
        });
    }
    
    private static void testAddCustomer(JFrame parent) {
        CustomerDialog dialog = new CustomerDialog(parent);
        dialog.setVisible(true);
        
        if (dialog.isConfirmed()) {
            Customer customer = dialog.getCustomer();
            System.out.println("New customer: " + customer);
            
            // Uncomment to actually add to database
            // boolean success = CustomerDAO.addCustomer(customer);
            // if (success) {
            //     JOptionPane.showMessageDialog(parent, "Customer added successfully!");
            // }
        } else {
            System.out.println("Add customer canceled");
        }
    }
    
    private static void testEditCustomer(JFrame parent) {
        // Get the first customer from the database for testing
        Customer customer = CustomerDAO.getAllCustomers().isEmpty() ? null : CustomerDAO.getAllCustomers().get(0);
        
        if (customer == null) {
            JOptionPane.showMessageDialog(parent, "No customers found to edit", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        CustomerDialog dialog = new CustomerDialog(parent, customer);
        dialog.setVisible(true);
        
        if (dialog.isConfirmed()) {
            customer = dialog.getCustomer();
            System.out.println("Edited customer: " + customer);
            
            // Uncomment to actually update in database
            // boolean success = CustomerDAO.updateCustomer(customer);
            // if (success) {
            //     JOptionPane.showMessageDialog(parent, "Customer updated successfully!");
            // }
        } else {
            System.out.println("Edit customer canceled");
        }
    }
}
