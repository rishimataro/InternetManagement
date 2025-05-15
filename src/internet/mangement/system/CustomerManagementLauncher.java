/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package internet.mangement.system;

/**
 * Launcher for CustomerManagementForm
 * @author ADMIN
 */
public class CustomerManagementLauncher {
    
    /**
     * Main method
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // Launch the CustomerManagementForm
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CustomerManagementForm().setVisible(true);
            }
        });
    }
}
