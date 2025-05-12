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
public class Contract {
    private int contract_id;
    private int sub_id;
    private int package_id;
    private LocalDateTime date_sign;
    private LocalDateTime date_expiration;
    private String address;
    private LocalDateTime create_at;

    public Contract() {
    }

    public Contract(int contract_id, int sub_id, int package_id, LocalDateTime date_sign, LocalDateTime date_expiration, String address, LocalDateTime create_at) {
        this.contract_id = contract_id;
        this.sub_id = sub_id;
        this.package_id = package_id;
        this.date_sign = date_sign;
        this.date_expiration = date_expiration;
        this.address = address;
        this.create_at = create_at;
    }

    public int getContract_id() {
        return contract_id;
    }

    public void setContract_id(int contract_id) {
        this.contract_id = contract_id;
    }

    public int getSub_id() {
        return sub_id;
    }

    public void setSub_id(int sub_id) {
        this.sub_id = sub_id;
    }

    public int getPackage_id() {
        return package_id;
    }

    public void setPackage_id(int package_id) {
        this.package_id = package_id;
    }

    public LocalDateTime getDate_sign() {
        return date_sign;
    }

    public void setDate_sign(LocalDateTime date_sign) {
        this.date_sign = date_sign;
    }

    public LocalDateTime getDate_expiration() {
        return date_expiration;
    }

    public void setDate_expiration(LocalDateTime date_expiration) {
        this.date_expiration = date_expiration;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getCreate_at() {
        return create_at;
    }

    public void setCreate_at(LocalDateTime create_at) {
        this.create_at = create_at;
    }
    
    
}
