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
public class User {
    private int user_id;
    private String username;
    private String password;
    private String role;
    private LocalDateTime create_at;
    private boolean isActive;

    public User() {
    }

    public User(int user_id, String username, String password, String role, LocalDateTime create_at, boolean isActive) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.create_at = create_at;
        this.isActive = isActive;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getCreate_at() {
        return create_at;
    }

    public void setCreate_at(LocalDateTime create_at) {
        this.create_at = create_at;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    
}
