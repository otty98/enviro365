package com.enviro.assessment.grad001.owethupeyane.enviro365.api.controller;

import com.enviro.assessment.grad001.owethupeyane.enviro365.api.model.Product;
import com.enviro.assessment.grad001.owethupeyane.enviro365.api.repo.ProductType;
import com.enviro.assessment.grad001.owethupeyane.enviro365.api.respond.ApiResponder;
import com.enviro.assessment.grad001.owethupeyane.enviro365.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class responsible for handling HTTP requests related to products.
 * This class defines REST endpoints for creating investments and retrieving investment information.
 */
@RestController
public class ProductController extends ApiResponder {

    private final ProductService productService;

    /**
     * Constructor to inject ProductService dependency.
     * @param productService ProductService instance for handling business logic related to products.
     */
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * REST endpoint to create a new investment.
     * @param product Product object containing investment details.
     * @return ResponseEntity containing HTTP status and response body.
     */
    @PostMapping("/product")
    public ResponseEntity<Object> createInvestment(@RequestBody Product product) {
        String investorID = product.getInvestorID();
        String productName = product.getProductName();
        String productType = product.getProductType();
        double balance = product.getCurrentBalance();

        // Validate investment details
        if (productType == null) {
            return getResponseError(HttpStatus.BAD_REQUEST, "Invalid investment plan, please choose between SAVINGS or RETIREMENT");
        } else if (investorID == null) {
            return getResponseError(HttpStatus.BAD_REQUEST, "You need to be signed on in order to properly identity you with your investment");
        } else if (balance <= 0) {
            return getResponseError(HttpStatus.BAD_REQUEST, "Cannot invest amount equal or less than R 0.00");
        }

        // Create investment
        return getResponse(HttpStatus.OK, productService.createInvestment(investorID, productName, productType, balance));
    }

    /**
     * REST endpoint to retrieve investment information by investor ID.
     * @param id Investor ID.
     * @return ResponseEntity containing HTTP status and response body.
     */
    @GetMapping("/product/{id}")
    public ResponseEntity<Object> getInvestments(@PathVariable String id) {
        return getResponse(HttpStatus.OK, productService.getInvestment(id));
    }
}

