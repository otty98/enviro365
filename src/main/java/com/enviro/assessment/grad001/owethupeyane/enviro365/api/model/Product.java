package com.enviro.assessment.grad001.owethupeyane.enviro365.api.model;

import com.enviro.assessment.grad001.owethupeyane.enviro365.api.repo.ProductType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Product {
    @Id
    private String productID;
    String investorID, productName, productType;
    private double currentBalance;

     /**
     * Default constructor.
     */
    public Product() {}

    /**
     * Constructor to create a product with generated ID.
     * @param investorID ID of the investor associated with the product.
     * @param productName Name of the product.
     * @param productType Type of the product (SAVINGS or RETIREMENT).
     * @param balance Current balance of the product.
     */

    public Product(String investorID, String productName, String productType, double balance) {
        this.investorID = investorID;
        this.productID = UUID.randomUUID().toString();
        this.productName = productName;
        this.currentBalance = balance;
        setProductType(productType);
    }

    /**
     * Constructor to create a product with specified ID.
     * @param investorID ID of the investor associated with the product.
     * @param productID ID of the product.
     * @param productName Name of the product.
     * @param productType Type of the product (SAVINGS or RETIREMENT).
     * @param balance Current balance of the product.
     */

    public Product(String investorID, String productID, String productName, String productType, double balance) {
        this(investorID, productName, productType, balance);
        this.productID = productID;
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
     * Getter for investor ID associated with the product.
     * @return Investor ID.
     */
    public String getInvestorID() {
        return investorID;
    }

     /**
     * Setter for investor ID associated with the product.
     * @param investorID Investor ID to set.
     */
    public void setInvestorID(String investorID) {
        this.investorID = investorID;
    }

    /**
     * Getter for product type.
     * @return Product type.
     */

    public String getProductType() {
        return productType;
    }

    /**
     * Setter for product type.
     * @param productType Product type to set (SAVINGS or RETIREMENT).
     */
    public void setProductType(String productType) {
        switch (productType.toUpperCase()) {
            case "SAVINGS":
                this.productType = ProductType.SAVINGS.getProductType();
                break;
            case "RETIREMENT":
                this.productType = ProductType.RETIREMENT.getProductType();
                break;
            default:
                this.productType = null;
        }
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }
}
