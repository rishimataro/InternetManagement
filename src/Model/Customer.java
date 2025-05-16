/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDateTime;

/**
 * Customer model class - extends Subscriber for compatibility
 * @author ADMIN
 */
public class Customer extends Subscriber {
    
    public Customer() {
        super();
    }
    
    public Customer(int subscriber_id, String fullName, String address, String phone) {
        super(subscriber_id, fullName, address, phone);
    }
    
    public Customer(int subscriber_id, String fullName, String address, String phone, 
            int user_id, String username, String password, String role, 
            LocalDateTime create_at, boolean isActive) {
        super(subscriber_id, fullName, address, phone, user_id, username, password, role, create_at, isActive);
    }
    
    @Override
    public String toString() {
        return "Customer{" + 
                "subscriber_id=" + getSubscriber_id() + 
                ", fullName=" + getFullName() + 
                ", address=" + getAddress() + 
                ", phone=" + getPhone() + 
                ", user_id=" + getUser_id() + 
                "}";
    }
}
