package com.enviro.assessment.grad001.owethupeyane.enviro365.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

/**
 * Entity class representing an investor.
 */
@Entity
public class Investor {

    @Id
    private String id;
    private String name;
    private String address;
    private String contact;
    private int age;

    /**
     * Default constructor.
     */
    public Investor() {}

    /**
     * Constructor to create an investor with generated ID.
     * @param name Investor's name.
     * @param address Investor's address.
     * @param contact Investor's contact information.
     * @param age Investor's age.
     */
    public Investor(String name, String address, String contact, int age) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.age = age;
    }

    /**
     * Constructor to create an investor with specified ID.
     * @param id Investor's ID.
     * @param name Investor's name.
     * @param address Investor's address.
     * @param contact Investor's contact information.
     * @param age Investor's age.
     */
    public Investor(String id, String name, String address, String contact, int age) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.age = age;
    }

    /**
     * Getter for investor's age.
     * @return Investor's age.
     */
    public int getAge() {
        return age;
    }

    /**
     * Setter for investor's age.
     * @param age Investor's age to set.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Getter for investor's ID.
     * @return Investor's ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Setter for investor's ID.
     * @param id Investor's ID to set.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter for investor's name.
     * @return Investor's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for investor's name.
     * @param name Investor's name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for investor's address.
     * @return Investor's address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter for investor's address.
     * @param address Investor's address to set.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Getter for investor's contact information.
     * @return Investor's contact information.
     */
    public String getContact() {
        return contact;
    }

    /**
     * Setter for investor's contact information.
     * @param contact Investor's contact information to set.
     */
    public void setContact(String contact) {
        this.contact = contact;
    }
}

