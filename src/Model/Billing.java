/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Model class for Billing information
 * @author ADMIN
 */
public class Billing {
    private int billingId;
    private int contractId;
    private LocalDate billingPeriod;
    private double amount;
    private LocalDateTime paymentDate;
    private String status;

    /**
     * Default constructor
     */
    public Billing() {
    }

    /**
     * Constructor with all fields
     * @param billingId Billing ID
     * @param contractId Contract ID
     * @param billingPeriod Billing period
     * @param amount Amount paid
     * @param paymentDate Payment date and time
     * @param status Payment status
     */
    public Billing(int billingId, int contractId, LocalDate billingPeriod, double amount, LocalDateTime paymentDate, String status) {
        this.billingId = billingId;
        this.contractId = contractId;
        this.billingPeriod = billingPeriod;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.status = status;
    }

    /**
     * Get the billing ID
     * @return Billing ID
     */
    public int getBillingId() {
        return billingId;
    }

    /**
     * Set the billing ID
     * @param billingId Billing ID
     */
    public void setBillingId(int billingId) {
        this.billingId = billingId;
    }

    /**
     * Get the contract ID
     * @return Contract ID
     */
    public int getContractId() {
        return contractId;
    }

    /**
     * Set the contract ID
     * @param contractId Contract ID
     */
    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    /**
     * Get the billing period
     * @return Billing period
     */
    public LocalDate getBillingPeriod() {
        return billingPeriod;
    }

    /**
     * Set the billing period
     * @param billingPeriod Billing period
     */
    public void setBillingPeriod(LocalDate billingPeriod) {
        this.billingPeriod = billingPeriod;
    }

    /**
     * Get the amount
     * @return Amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Set the amount
     * @param amount Amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Get the payment date and time
     * @return Payment date and time
     */
    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    /**
     * Set the payment date and time
     * @param paymentDate Payment date and time
     */
    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    /**
     * Get the status
     * @return Status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Set the status
     * @param status Status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * String representation of the Billing object
     * @return String representation
     */
    @Override
    public String toString() {
        return "Billing{" +
                "billingId=" + billingId +
                ", contractId=" + contractId +
                ", billingPeriod=" + billingPeriod +
                ", amount=" + amount +
                ", paymentDate=" + paymentDate +
                ", status=" + status +
                '}';
    }
}
