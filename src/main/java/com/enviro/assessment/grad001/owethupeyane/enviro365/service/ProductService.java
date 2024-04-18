package com.enviro.assessment.grad001.owethupeyane.enviro365.service;

import com.enviro.assessment.grad001.owethupeyane.enviro365.api.model.Product;
import com.enviro.assessment.grad001.owethupeyane.enviro365.doa.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

/**
 * Service class for managing product-related operations.
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepo repo; // Repository for interacting with the database

    /**
     * Creates a new investment product for an investor.
     * @param investorID ID of the investor.
     * @param productName Name of the product.
     * @param productType Type of the product (SAVINGS or RETIREMENT).
     * @param balance Current balance of the product.
     * @return Map containing information about the created product, including ProductID and InvestorID.
     */
    public Map<String, String> createInvestment(String investorID, String productName, String productType, double balance) {
        try {
            Product product = new Product(investorID, productName, productType, balance);
            repo.save(product); // Save the new product to the repository
            return Map.of("ProductID", product.getProductID(), "InvestorID", product.getInvestorID());
        } catch (NullPointerException e) {
            return Map.of("Error", e.getMessage()); // Return error message if any exception occurs
        }
    }

    /**
     * Retrieves a list of investments associated with a specific investor ID.
     * @param id Investor ID.
     * @return List of Product objects representing the investments.
     */
    public List<Product> getInvestment(String id){
        Iterable<Product> products = repo.findAll(); // Retrieve all products from the repository
        return StreamSupport.stream(products.spliterator(), false)
                .filter(x -> x.getInvestorID().equals(id)) // Filter products by investor ID
                .toList(); // Convert filtered stream to list and return
    }
}

