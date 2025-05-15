/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package internet.mangement.system;

import Model.Customer;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * Dialog for adding or editing a customer
 * @author ADMIN
 */
public class CustomerDialog extends JDialog {
    
    private JTextField txtFullName;
    private JTextField txtAddress;
    private JTextField txtPhone;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JCheckBox chkActive;
    
    private Customer customer;
    private boolean isNewCustomer;
    private boolean confirmed = false;
    
    /**
     * Create a dialog for adding a new customer
     * @param parent The parent frame
     */
    public CustomerDialog(JFrame parent) {
        this(parent, null);
    }
    
    /**
     * Create a dialog for editing an existing customer
     * @param parent The parent frame
     * @param customer The customer to edit
     */
    public CustomerDialog(JFrame parent, Customer customer) {
        super(parent, customer == null ? "Thêm khách hàng mới" : "Sửa thông tin khách hàng", true);
        this.customer = customer;
        this.isNewCustomer = (customer == null);
        
        if (this.customer == null) {
            this.customer = new Customer();
            this.customer.setRole("user");
            this.customer.setIsActive(true);
            this.customer.setCreate_at(LocalDateTime.now());
        }
        
        initComponents();
        loadCustomerData();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout());
        
        // Main panel with form fields
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Full Name
        mainPanel.add(new JLabel("Tên khách hàng:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        txtFullName = new JTextField(20);
        mainPanel.add(txtFullName, gbc);
        
        // Address
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        mainPanel.add(new JLabel("Địa chỉ:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        txtAddress = new JTextField(20);
        mainPanel.add(txtAddress, gbc);
        
        // Phone
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        mainPanel.add(new JLabel("Số điện thoại:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        txtPhone = new JTextField(20);
        mainPanel.add(txtPhone, gbc);
        
        // Username
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        mainPanel.add(new JLabel("Tên đăng nhập:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        txtUsername = new JTextField(20);
        mainPanel.add(txtUsername, gbc);
        
        // Password
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0;
        mainPanel.add(new JLabel("Mật khẩu:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        txtPassword = new JPasswordField(20);
        mainPanel.add(txtPassword, gbc);
        
        // Active status
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 0;
        mainPanel.add(new JLabel("Trạng thái:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        chkActive = new JCheckBox("Hoạt động");
        mainPanel.add(chkActive, gbc);
        
        add(mainPanel, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnSave = new JButton("Lưu");
        JButton btnCancel = new JButton("Hủy");
        
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateInputs()) {
                    saveCustomerData();
                    confirmed = true;
                    dispose();
                }
            }
        });
        
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);
        
        add(buttonPanel, BorderLayout.SOUTH);
        
        pack();
        setLocationRelativeTo(getParent());
        setResizable(false);
    }
    
    private void loadCustomerData() {
        if (!isNewCustomer) {
            txtFullName.setText(customer.getFullName());
            txtAddress.setText(customer.getAddress());
            txtPhone.setText(customer.getPhone());
            txtUsername.setText(customer.getUsername());
            txtPassword.setText(customer.getPassword());
            chkActive.setSelected(customer.isIsActive());
        }
    }
    
    private void saveCustomerData() {
        customer.setFullName(txtFullName.getText().trim());
        customer.setAddress(txtAddress.getText().trim());
        customer.setPhone(txtPhone.getText().trim());
        customer.setUsername(txtUsername.getText().trim());
        customer.setPassword(new String(txtPassword.getPassword()));
        customer.setIsActive(chkActive.isSelected());
    }
    
    private boolean validateInputs() {
        if (txtFullName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên khách hàng", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtFullName.requestFocus();
            return false;
        }
        
        if (txtAddress.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập địa chỉ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtAddress.requestFocus();
            return false;
        }
        
        if (txtPhone.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtPhone.requestFocus();
            return false;
        }
        
        // Validate phone number format
        if (!txtPhone.getText().trim().matches("^[0-9]*$")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại chỉ được chứa các chữ số", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtPhone.requestFocus();
            return false;
        }
        
        if (txtUsername.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên đăng nhập", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtUsername.requestFocus();
            return false;
        }
        
        if (isNewCustomer && new String(txtPassword.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txtPassword.requestFocus();
            return false;
        }
        
        return true;
    }
    
    /**
     * Get the customer with updated information
     * @return The customer object
     */
    public Customer getCustomer() {
        return customer;
    }
    
    /**
     * Check if the user confirmed the dialog
     * @return true if confirmed, false if canceled
     */
    public boolean isConfirmed() {
        return confirmed;
    }
}
