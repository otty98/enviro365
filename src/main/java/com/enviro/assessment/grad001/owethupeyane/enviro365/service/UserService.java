package com.enviro.assessment.grad001.owethupeyane.enviro365.service;

import com.enviro.assessment.grad001.owethupeyane.enviro365.api.model.Investor;
import com.enviro.assessment.grad001.owethupeyane.enviro365.doa.InvestorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service class for managing user-related operations.
 */
@Service
public class UserService {

    @Autowired
    private InvestorRepo investorRepo; // Repository for interacting with the database

    /**
     * Retrieves an investor by their ID.
     * @param id Investor ID.
     * @return Investor object if found, else null.
     */
    public Investor getInvestor(String id) {
        return investorRepo.findById(id).orElse(null);
    }

    /**
     * Registers a new investor.
     * @param name Investor's name.
     * @param address Investor's address.
     * @param contact Investor's contact information.
     * @param age Investor's age.
     * @return Map containing the name and ID of the registered investor.
     */
    public Map<String, String> registerInvestor(String name, String address, String contact, int age) {
        Investor investor = new Investor(name, address, contact, age); // Create a new Investor object
        investorRepo.save(investor); // Save the new investor to the repository
        return Map.of("name", investor.getName(), "id", investor.getId()); // Return map with investor name and ID
    }

    /**
     * Deletes an investor by their ID.
     * @param id Investor ID.
     * @return True if the investor was successfully deleted, false otherwise.
     */
    public boolean deleteInvestor(String id) {
        try {
            Investor investor = getInvestor(id); // Retrieve the investor by ID
            if (investor == null)
                throw new NullPointerException(); // Throw exception if investor is not found
            investorRepo.delete(investor); // Delete the investor from the repository
            return true;
        } catch (NullPointerException e) {
            return false; // Return false if there's an error or if the investor is not found
        }
    }

    /**
     * Updates an investor's information.
     * @param id Investor ID.
     * @param address New address (optional).
     * @param contact New contact information (optional).
     * @return Updated Investor object if successful, else null.
     */
    public Investor updateInvestor(String id, String address, String contact) {
        Investor investor = getInvestor(id); // Retrieve the investor by ID
        if (investor != null && deleteInvestor(id)) { // If investor exists and is successfully deleted
            // Update address and contact if provided
            if (address != null && !address.isBlank())
                investor.setAddress(address);
            if (contact != null && !contact.isBlank())
                investor.setContact(contact);

            // Save the updated investor to the repository
            investorRepo.save(new Investor(id, investor.getName(), investor.getAddress(), investor.getContact(), investor.getAge()));
        }

        return investor; // Return the updated investor object
    }
}

