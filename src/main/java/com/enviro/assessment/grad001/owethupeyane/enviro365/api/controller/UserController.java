package com.enviro.assessment.grad001.owethupeyane.enviro365.api.controller;

import com.enviro.assessment.grad001.owethupeyane.enviro365.api.respond.ApiResponder;
import com.enviro.assessment.grad001.owethupeyane.enviro365.api.model.Investor;
import com.enviro.assessment.grad001.owethupeyane.enviro365.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Controller class responsible for handling HTTP requests related to users (investors).
 * This class defines REST endpoints for managing investor resources.
 */
@RestController
public class UserController extends ApiResponder {

    private final UserService userService;

    /**
     * Constructor to inject UserService dependency.
     * @param userService UserService instance for handling business logic related to users.
     */
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    /**
     * REST endpoint to create a new investor.
     * @param investor Investor object containing investor details.
     * @return ResponseEntity containing HTTP status and response body.
     */
    @PostMapping("/investor")
    public ResponseEntity<Object> createUser(@RequestBody final Investor investor) {
        String name = investor.getName();
        String address = investor.getAddress();
        String contact = investor.getContact();
        int age = investor.getAge();

        // Validate investor details
        if ((name == null) || (address == null) || (contact == null)) {
            return getResponseError(HttpStatus.BAD_REQUEST, "Failed to create an investor, please provide enough investor information");
        } else if (age < 1) {
            return getResponseError(HttpStatus.BAD_REQUEST, "Age cannot be set to less than 1 year");
        }

        // Create investor
        return getResponse(HttpStatus.CREATED, userService.registerInvestor(name, address, contact, age));
    }

    /**
     * REST endpoint to retrieve investor information by investor ID.
     * @param id Investor ID.
     * @return ResponseEntity containing HTTP status and response body.
     */
    @GetMapping("/investor/{id}")
    public ResponseEntity<Object> getUser(@PathVariable String id){
        Investor investor = userService.getInvestor(id);
        if (investor == null) {
            return getResponseError(HttpStatus.NOT_FOUND, "ID requested does not exist");
        }
        return getResponse(HttpStatus.OK, investor);
    }

    /**
     * REST endpoint to delete an existing investor by investor ID.
     * @param id Investor ID.
     * @return ResponseEntity containing HTTP status and response body.
     */
    @DeleteMapping("/investor/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable String id) {
        boolean isDeleted = userService.deleteInvestor(id);
        if (isDeleted) {
            Map<String, Boolean> result = Map.of("success", true);
            return getResponse(HttpStatus.OK, result);
        }
        return getResponseError(HttpStatus.NOT_FOUND, "Failed to delete investor");
    }

    /**
     * REST endpoint to update an existing investor by investor ID.
     * @param id Investor ID.
     * @param investor Updated investor details.
     * @return ResponseEntity containing HTTP status and response body.
     */
    @PutMapping("/investor/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable String id, @RequestBody Investor investor) {
        String address = investor.getAddress();
        String contact = investor.getContact();

        // Update investor
        Investor updatedInvestor = userService.updateInvestor(id, address, contact);
        if (updatedInvestor != null) {
            return getResponse(HttpStatus.ACCEPTED, updatedInvestor);
        }

        return getResponseError(HttpStatus.BAD_REQUEST, "Failed to update investor");
    }
}

