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
public class StatisticalRecord {
    private int subscriber_id;
    private String customer_name;
    private String plan_name;
    private LocalDateTime registration_date;
    private String status;
    private long amount_paid;

    public StatisticalRecord() {
    }

    public StatisticalRecord(int subscriber_id, String customer_name, String plan_name, LocalDateTime registration_date, String status, long amount_paid) {
        this.subscriber_id = subscriber_id;
        this.customer_name = customer_name;
        this.plan_name = plan_name;
        this.registration_date = registration_date;
        this.status = status;
        this.amount_paid = amount_paid;
    }

    public int getSubscriber_id() {
        return subscriber_id;
    }

    public void setSubscriber_id(int subscriber_id) {
        this.subscriber_id = subscriber_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getPlan_name() {
        return plan_name;
    }

    public void setPlan_name(String plan_name) {
        this.plan_name = plan_name;
    }

    public LocalDateTime getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(LocalDateTime registration_date) {
        this.registration_date = registration_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getAmount_paid() {
        return amount_paid;
    }

    public void setAmount_paid(long amount_paid) {
        this.amount_paid = amount_paid;
    }
}
