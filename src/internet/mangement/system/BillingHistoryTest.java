/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package internet.mangement.system;

import DAO.BillingDAO;
import DAO.ConnectionProvider;
import Model.Billing;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Test class for BillingHistoryForm
 * @author ADMIN
 */
public class BillingHistoryTest {

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
     * Test retrieving all billing records
     */
    public static void testGetAllBillings() {
        System.out.println("Testing getAllBillings()...");
        List<Billing> billings = BillingDAO.getAllBillings();

        if (billings != null && !billings.isEmpty()) {
            System.out.println("Successfully retrieved " + billings.size() + " billing records:");
            for (Billing billing : billings) {
                System.out.println(billing);
            }
        } else {
            System.out.println("No billing records found or error retrieving billing records.");
        }
    }

    /**
     * Test retrieving a billing record by ID
     */
    public static void testGetBillingById() {
        System.out.println("Testing getBillingById()...");

        // Try to get the first billing record
        List<Billing> billings = BillingDAO.getAllBillings();
        if (billings != null && !billings.isEmpty()) {
            int billingId = billings.get(0).getBillingId();
            Billing billing = BillingDAO.getBillingById(billingId);

            if (billing != null) {
                System.out.println("Successfully retrieved billing record with ID " + billingId + ":");
                System.out.println(billing);
            } else {
                System.out.println("Failed to retrieve billing record with ID " + billingId + ".");
            }
        } else {
            System.out.println("No billing records found to test getBillingById().");
        }
    }

    /**
     * Test retrieving billing records by contract ID
     */
    public static void testGetBillingsByContractId() {
        System.out.println("Testing getBillingsByContractId()...");

        // Try to get the first billing record's contract ID
        List<Billing> billings = BillingDAO.getAllBillings();
        if (billings != null && !billings.isEmpty()) {
            int contractId = billings.get(0).getContractId();
            List<Billing> contractBillings = BillingDAO.getBillingsByContractId(contractId);

            if (contractBillings != null && !contractBillings.isEmpty()) {
                System.out.println("Successfully retrieved " + contractBillings.size() + " billing records for contract ID " + contractId + ":");
                for (Billing billing : contractBillings) {
                    System.out.println(billing);
                }
            } else {
                System.out.println("No billing records found for contract ID " + contractId + ".");
            }
        } else {
            System.out.println("No billing records found to test getBillingsByContractId().");
        }
    }

    /**
     * Test adding a new billing record
     */
    public static void testAddBilling() {
        System.out.println("Testing addBilling()...");

        Billing newBilling = new Billing();
        newBilling.setContractId(1); // Assuming contract ID 1 exists
        newBilling.setBillingPeriod(LocalDate.now());
        newBilling.setAmount(500000);
        newBilling.setStatus("paid");
        newBilling.setPaymentDate(LocalDateTime.now());

        boolean success = BillingDAO.addBilling(newBilling);

        if (success) {
            System.out.println("Successfully added new billing record with ID: " + newBilling.getBillingId());
        } else {
            System.out.println("Failed to add new billing record.");
        }
    }

    /**
     * Run all tests
     */
    public static void runAllTests() {
        if (testConnection()) {
            testGetAllBillings();
            testGetBillingById();
            testGetBillingsByContractId();
            // Uncomment to test adding a new billing record
            // testAddBilling();
        }
    }

    /**
     * Main method
     * @param args command line arguments
     */
    public static void main(String[] args) {
        runAllTests();

        // Launch the BillingHistoryForm
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BillingHistoryForm().setVisible(true);
            }
        });
    }
}
