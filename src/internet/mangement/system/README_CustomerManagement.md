# Customer Management System

This package contains classes for managing customers in the Internet Management System.

## Overview

The customer management system consists of the following components:

1. **CustomerManagementForm** - The main form for displaying and managing customers
2. **CustomerManagementFormEnhanced** - Enhanced version that uses the controller
3. **CustomerFormController** - Controller class for handling events and database operations
4. **CustomerDialog** - Dialog for adding and editing customers
5. **CustomerDAO** - Data Access Object for customer database operations
6. **Customer** - Model class representing a customer

## How to Use

### Using the Enhanced Form

The easiest way to use the customer management system is to run the `CustomerManagementFormEnhanced` class:

```java
public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        new CustomerManagementFormEnhanced().setVisible(true);
    });
}
```

This form automatically initializes the controller and connects all the event handlers.

### Using the Controller with the Original Form

If you want to use the controller with the original form, you can create an instance of the controller and pass the form to it:

```java
CustomerManagementForm form = new CustomerManagementForm();
CustomerFormController controller = new CustomerFormController(form);
form.setVisible(true);
```

The controller will automatically connect to the form's components and handle all events.

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

## Implementation Details

### CustomerFormController

The `CustomerFormController` class uses reflection to access the form's components. This allows it to work with the original form without modifying it. The controller:

1. Gets references to all form components
2. Adds event listeners to the components
3. Handles all database operations through the CustomerDAO

### CustomerDialog

The `CustomerDialog` class provides a reusable dialog for adding and editing customers. It:

1. Creates a form with fields for all customer properties
2. Validates user input
3. Returns the customer object with updated information

### CustomerDAO

The `CustomerDAO` class handles all database operations for customers. It:

1. Connects to the database using ConnectionProvider
2. Executes SQL queries using DbOperations
3. Converts between database records and Customer objects

## Troubleshooting

If you encounter issues with the customer management system:

1. Check the database connection in ConnectionProvider
2. Verify that the USER and SUBSCRIBER tables exist with the correct schema
3. Make sure the form components match the expected names in CustomerFormController
4. Check the console for any exceptions or error messages
