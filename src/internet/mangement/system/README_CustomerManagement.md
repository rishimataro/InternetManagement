# Customer Management System

This package contains classes for managing customers in the Internet Management System.

## Overview

The customer management system consists of the following components:

1. **CustomerManagementForm** - The main form for displaying and managing customers
2. **CustomerManagementFormHelper** - Helper class for handling database operations
3. **CustomerDialog** - Dialog for adding and editing customers
4. **CustomerDAO** - Data Access Object for customer database operations
5. **Customer** - Model class representing a customer

## How to Use

### Using the CustomerManagementFormHelper

The `CustomerManagementFormHelper` class is designed to work with the existing `CustomerManagementForm` without modifying it. To use it:

1. Create an instance of `CustomerManagementFormHelper` in your form:

```java
private CustomerManagementFormHelper helper;

public CustomerManagementForm() {
    initComponents();
    setTitle("Quản lý khách hàng");
    
    // Initialize the helper
    helper = new CustomerManagementFormHelper(this, jTable1);
    
    // Load customer data
    helper.loadCustomerData();
}
```

2. Call the helper methods from your form's event handlers:

```java
private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {
    String name = txtSearchName.getText().trim();
    String phone = txtSearchPhone.getText().trim();
    helper.searchCustomers(name, phone);
}

private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {
    txtSearchName.setText("");
    txtSearchPhone.setText("");
    helper.loadCustomerData();
}

private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {
    helper.addCustomer();
}

private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {
    helper.editCustomer();
}

private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {
    helper.deleteCustomer();
}
```

### Using the CustomerDialog

The `CustomerDialog` class provides a dialog for adding and editing customers:

```java
// To add a new customer
CustomerDialog dialog = new CustomerDialog(parentFrame);
dialog.setVisible(true);

if (dialog.isConfirmed()) {
    Customer customer = dialog.getCustomer();
    boolean success = CustomerDAO.addCustomer(customer);
    // Handle result
}

// To edit an existing customer
Customer customer = CustomerDAO.getCustomerById(customerId);
CustomerDialog dialog = new CustomerDialog(parentFrame, customer);
dialog.setVisible(true);

if (dialog.isConfirmed()) {
    customer = dialog.getCustomer();
    boolean success = CustomerDAO.updateCustomer(customer);
    // Handle result
}
```

## Testing

Two test classes are provided:

1. **CustomerDialogTest** - Tests the CustomerDialog for adding and editing customers
2. **CustomerManagementFormTest** - Tests the CustomerManagementForm with the helper

To run the tests, execute the main method in either class.

## Database Schema

The customer management system uses two tables:

1. **USER** - Stores user authentication information
   - user_id (PK)
   - username
   - password
   - role
   - created_at
   - isActive

2. **SUBSCRIBER** - Stores customer information
   - subscriber_id (PK)
   - name
   - address
   - phone
   - user_id (FK to USER.user_id)

## Class Hierarchy

- User
  - Subscriber
    - Customer

The Customer class extends Subscriber, which extends User, providing a complete model for customer management.
