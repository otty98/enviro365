package com.enviro.assessment.grad001.owethupeyane.enviro365.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

/**
 * Entity class representing a withdrawal from a financial product.
 */
@Entity
public class Withdrawal {
    @Id
    private String withdrawalID;
    private String investorID;
    private String productID;
    private String withdrawResults;
    private double amount;

    /**
     * Default constructor.
     */
    public Withdrawal() {}

    /**
     * Constructor to create a withdrawal with generated ID.
     * @param investorID ID of the investor initiating the withdrawal.
     * @param productID ID of the financial product from which the withdrawal is made.
     * @param amount Amount to be withdrawn.
     */
    public Withdrawal(String investorID, String productID, double amount) {
        this.withdrawalID = UUID.randomUUID().toString();
        this.investorID = investorID;
        this.productID = productID;
        this.amount = amount;
    }

    /**
     * Getter for withdrawal ID.
     * @return Withdrawal ID.
     */
    public String getWithdrawalID() {
        return withdrawalID;
    }

    /**
     * Setter for withdrawal ID.
     * @param withdrawalID Withdrawal ID to set.
     */
    public void setWithdrawalID(String withdrawalID) {
        this.withdrawalID = withdrawalID;
    }

    /**
     * Getter for investor ID.
     * @return Investor ID.
     */
    public String getInvestorID() {
        return investorID;
    }

    /**
     * Setter for investor ID.
     * @param investorID Investor ID to set.
     */
    public void setInvestorID(String investorID) {
        this.investorID = investorID;
    }

    /**
     * Getter for product ID.
     * @return Product ID.
     */
    public String getProductID() {
        return productID;
    }

    /**
     * Setter for product ID.
     * @param productID Product ID to set.
     */
    public void setProductID(String productID) {
        this.productID = productID;
    }

    /**
     * Getter for withdrawal results.
     * @return Withdrawal results.
     */
    public String getWithdrawResults() {
        return withdrawResults;
    }

    /**
     * Setter for withdrawal results.
     * @param withdrawResults Withdrawal results to set.
     */
    public void setWithdrawResults(String withdrawResults) {
        this.withdrawResults = withdrawResults;
    }

    /**
     * Getter for withdrawal amount.
     * @return Withdrawal amount.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Setter for withdrawal amount.
     * @param amount Amount to set.
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }
}

