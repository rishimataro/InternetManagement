/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package internet.mangement.system;

import DAO.CustomerDAO;
import Model.Customer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 * Controller class for CustomerManagementForm
 * @author ADMIN
 */
public class CustomerFormController {
    
    private CustomerManagementForm form;
    private JTable customerTable;
    private JTextField txtCustomerId;
    private JTextField txtCustomerName;
    private JTextField txtAddress;
    private JTextField txtPhone;
    private JTextField txtUserId;
    private JTextField txtSearchId;
    private JTextField txtSearchName;
    private JButton btnSearch;
    private JButton btnClear;
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;
    private JButton btnReturn;
    
    private List<Customer> customerList;
    
    /**
     * Constructor
     * @param form The CustomerManagementForm instance
     */
    public CustomerFormController(CustomerManagementForm form) {
        this.form = form;
        
        // Initialize components
        initComponents();
        
        // Add event listeners
        addEventListeners();
        
        // Load initial data
        loadCustomerData();
    }
    
    /**
     * Initialize components from the form
     */
    private void initComponents() {
        // Get components from the form using reflection
        try {
            java.lang.reflect.Field tableField = CustomerManagementForm.class.getDeclaredField("jTable1");
            tableField.setAccessible(true);
            customerTable = (JTable) tableField.get(form);
            
            java.lang.reflect.Field txtCustomerIdField = CustomerManagementForm.class.getDeclaredField("jTextField3");
            txtCustomerIdField.setAccessible(true);
            txtCustomerId = (JTextField) txtCustomerIdField.get(form);
            
            java.lang.reflect.Field txtCustomerNameField = CustomerManagementForm.class.getDeclaredField("jTextField4");
            txtCustomerNameField.setAccessible(true);
            txtCustomerName = (JTextField) txtCustomerNameField.get(form);
            
            java.lang.reflect.Field txtAddressField = CustomerManagementForm.class.getDeclaredField("jTextField5");
            txtAddressField.setAccessible(true);
            txtAddress = (JTextField) txtAddressField.get(form);
            
            java.lang.reflect.Field txtPhoneField = CustomerManagementForm.class.getDeclaredField("jTextField6");
            txtPhoneField.setAccessible(true);
            txtPhone = (JTextField) txtPhoneField.get(form);
            
            java.lang.reflect.Field txtUserIdField = CustomerManagementForm.class.getDeclaredField("jTextField7");
            txtUserIdField.setAccessible(true);
            txtUserId = (JTextField) txtUserIdField.get(form);
            
            java.lang.reflect.Field txtSearchIdField = CustomerManagementForm.class.getDeclaredField("jTextField1");
            txtSearchIdField.setAccessible(true);
            txtSearchId = (JTextField) txtSearchIdField.get(form);
            
            java.lang.reflect.Field txtSearchNameField = CustomerManagementForm.class.getDeclaredField("jTextField2");
            txtSearchNameField.setAccessible(true);
            txtSearchName = (JTextField) txtSearchNameField.get(form);
            
            java.lang.reflect.Field btnSearchField = CustomerManagementForm.class.getDeclaredField("jButton2");
            btnSearchField.setAccessible(true);
            btnSearch = (JButton) btnSearchField.get(form);
            
            java.lang.reflect.Field btnClearField = CustomerManagementForm.class.getDeclaredField("jButton1");
            btnClearField.setAccessible(true);
            btnClear = (JButton) btnClearField.get(form);
            
            java.lang.reflect.Field btnAddField = CustomerManagementForm.class.getDeclaredField("jButton3");
            btnAddField.setAccessible(true);
            btnAdd = (JButton) btnAddField.get(form);
            
            java.lang.reflect.Field btnEditField = CustomerManagementForm.class.getDeclaredField("jButton4");
            btnEditField.setAccessible(true);
            btnEdit = (JButton) btnEditField.get(form);
            
            java.lang.reflect.Field btnDeleteField = CustomerManagementForm.class.getDeclaredField("jButton5");
            btnDeleteField.setAccessible(true);
            btnDelete = (JButton) btnDeleteField.get(form);
            
            java.lang.reflect.Field btnReturnField = CustomerManagementForm.class.getDeclaredField("jButton6");
            btnReturnField.setAccessible(true);
            btnReturn = (JButton) btnReturnField.get(form);
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(form, "Error initializing controller: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Add event listeners to components
     */
    private void addEventListeners() {
        // Table selection listener
        customerTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && customerTable.getSelectedRow() != -1) {
                    displaySelectedCustomer();
                }
            }
        });
        
        // Button listeners
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchCustomers();
            }
        });
        
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearSearch();
            }
        });
        
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCustomer();
            }
        });
        
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editCustomer();
            }
        });
        
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteCustomer();
            }
        });
        
        btnReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                form.dispose();
            }
        });
    }
    
    /**
     * Load customer data from the database
     */
    public void loadCustomerData() {
        customerList = CustomerDAO.getAllCustomers();
        displayCustomersInTable(customerList);
    }
    
    /**
     * Display customers in the table
     * @param customers List of customers to display
     */
    private void displayCustomersInTable(List<Customer> customers) {
        DefaultTableModel model = (DefaultTableModel) customerTable.getModel();
        model.setRowCount(0); // Clear existing rows
        
        for (Customer customer : customers) {
            Object[] row = new Object[5];
            row[0] = customer.getSubscriber_id();
            row[1] = customer.getFullName();
            row[2] = customer.getAddress();
            row[3] = customer.getPhone();
            row[4] = customer.getUser_id();
            model.addRow(row);
        }
    }
    
    /**
     * Display the selected customer in the form fields
     */
    private void displaySelectedCustomer() {
        int selectedRow = customerTable.getSelectedRow();
        if (selectedRow != -1) {
            txtCustomerId.setText(customerTable.getValueAt(selectedRow, 0).toString());
            txtCustomerName.setText(customerTable.getValueAt(selectedRow, 1).toString());
            txtAddress.setText(customerTable.getValueAt(selectedRow, 2).toString());
            txtPhone.setText(customerTable.getValueAt(selectedRow, 3).toString());
            txtUserId.setText(customerTable.getValueAt(selectedRow, 4).toString());
        }
    }
    
    /**
     * Search for customers based on criteria
     */
    private void searchCustomers() {
        String id = txtSearchId.getText().trim();
        String name = txtSearchName.getText().trim();
        
        List<Customer> searchResults = CustomerDAO.searchCustomers(name, "");
        displayCustomersInTable(searchResults);
    }
    
    /**
     * Clear search fields and refresh the table
     */
    private void clearSearch() {
        txtSearchId.setText("");
        txtSearchName.setText("");
        loadCustomerData();
    }
    
    /**
     * Add a new customer
     */
    private void addCustomer() {
        CustomerDialog dialog = new CustomerDialog(form);
        dialog.setVisible(true);
        
        if (dialog.isConfirmed()) {
            Customer customer = dialog.getCustomer();
            boolean success = CustomerDAO.addCustomer(customer);
            
            if (success) {
                loadCustomerData();
                JOptionPane.showMessageDialog(form, "Thêm khách hàng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    /**
     * Edit the selected customer
     */
    private void editCustomer() {
        int selectedRow = customerTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(form, "Vui lòng chọn một khách hàng để sửa", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int customerId = Integer.parseInt(txtCustomerId.getText());
        Customer customer = CustomerDAO.getCustomerById(customerId);
        
        if (customer != null) {
            CustomerDialog dialog = new CustomerDialog(form, customer);
            dialog.setVisible(true);
            
            if (dialog.isConfirmed()) {
                customer = dialog.getCustomer();
                boolean success = CustomerDAO.updateCustomer(customer);
                
                if (success) {
                    loadCustomerData();
                    JOptionPane.showMessageDialog(form, "Cập nhật khách hàng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }
    
    /**
     * Delete the selected customer
     */
    private void deleteCustomer() {
        int selectedRow = customerTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(form, "Vui lòng chọn một khách hàng để xóa", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int customerId = Integer.parseInt(txtCustomerId.getText());
        
        int confirm = JOptionPane.showConfirmDialog(form,
                "Bạn có chắc chắn muốn xóa khách hàng này?",
                "Xác nhận xóa",
                JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = CustomerDAO.deleteCustomer(customerId);
            
            if (success) {
                loadCustomerData();
                clearFields();
                JOptionPane.showMessageDialog(form, "Xóa khách hàng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    /**
     * Clear form fields
     */
    private void clearFields() {
        txtCustomerId.setText("");
        txtCustomerName.setText("");
        txtAddress.setText("");
        txtPhone.setText("");
        txtUserId.setText("");
    }
}
