/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDateTime;

/**
 *
 * @author Ngoc Thao
 */
public class Subscriber extends User {
    private int subscriber_id;
    private String fullName;
    private String address;
    private String phone;
    
    public Subscriber() {
    }

    public Subscriber(int subscriber_id, String fullName, String address, String phone) {
        this.subscriber_id = subscriber_id;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
    }

    public Subscriber(int subscriber_id, String fullName, String address, String phone, int user_id, String username, String password, String role, LocalDateTime create_at) {
        super(user_id, username, password, role, create_at);
        this.subscriber_id = subscriber_id;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
    }
    
    public int getSubscriber_id() {
        return subscriber_id;
    }

    public void setSubscriber_id(int subscriber_id) {
        this.subscriber_id = subscriber_id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    
}
