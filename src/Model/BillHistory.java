/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author Ngoc Thao
 */
public class BillHistory {
    private int bill_id;
    private int contract_id;
    private LocalDate billing_period;
    private long amount;
    private LocalDateTime payment_date;
    private String status;   

    public BillHistory() {
    }

    public BillHistory(int bill_id, int contract_id, LocalDate billing_period, long amount, LocalDateTime payment_date, String status) {
        this.bill_id = bill_id;
        this.contract_id = contract_id;
        this.billing_period = billing_period;
        this.amount = amount;
        this.payment_date = payment_date;
        this.status = status;
    }

    public int getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public int getContract_id() {
        return contract_id;
    }

    public void setContract_id(int contract_id) {
        this.contract_id = contract_id;
    }

    public LocalDate getBilling_period() {
        return billing_period;
    }

    public void setBilling_period(LocalDate billing_period) {
        this.billing_period = billing_period;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public LocalDateTime getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(LocalDateTime payment_date) {
        this.payment_date = payment_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
