/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package internet.mangement.system;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 * Launcher for BillingHistoryForm
 * @author ADMIN
 */
public class BillingHistoryLauncher {
    
    /**
     * Main method
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // Set up FlatLaf Look and Feel
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            // If FlatLaf setup fails, try to use the system look and feel
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, 
                        "Failed to initialize Look and Feel: " + e.getMessage(), 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        
        // Launch the BillingHistoryForm
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BillingHistoryForm().setVisible(true);
            }
        });
    }
}
