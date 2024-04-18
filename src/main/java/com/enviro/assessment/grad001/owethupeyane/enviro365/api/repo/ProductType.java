package com.enviro.assessment.grad001.owethupeyane.enviro365.api.repo;

/**
 * Enum representing types of financial products.
 */
public enum ProductType {
    SAVINGS("SAVINGS", 0),       // Savings product type
    RETIREMENT("RETIREMENT", 65); // Retirement product type with a withdrawal age limit of 65

    private final String productType; // Type of the product
    private final int withdrawAgeLimit; // Age limit for withdrawal (applies only to RETIREMENT product)

    /**
     * Constructor for ProductType enum.
     * @param type Type of the product.
     * @param limit Withdrawal age limit for retirement product.
     */
    ProductType(String type, int limit) {
        this.productType = type;
        this.withdrawAgeLimit = limit;
    }

    /**
     * Getter for the product type.
     * @return Product type.
     */
    public String getProductType() {
        return productType;
    }

    /**
     * Getter for the withdrawal age limit for retirement product.
     * @return Withdrawal age limit.
     */
    public int getWithdrawAgeLimit() {
        return withdrawAgeLimit;
    }
}

