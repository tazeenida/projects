package com.acs560.FoodManagementSystem.models;

import lombok.*;

/**
 * Represents a customer in the food management system.
 */
@Data
@NoArgsConstructor
public class Customer {
    
    /**
     * Unique identifier for the customer.
     */
    private Integer customerId;

    /**
     * Rating given to the customer.
     * This could be used for feedback or loyalty points.
     */
    private Float rating;
}
