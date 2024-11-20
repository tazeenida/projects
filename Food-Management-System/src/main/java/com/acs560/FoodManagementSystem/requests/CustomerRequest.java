package com.acs560.FoodManagementSystem.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a request object for creating or updating customer information.
 * This class contains the customer's rating, which is required.
 */
@Data
@NoArgsConstructor
public class CustomerRequest {

    /**
     * The rating of the customer.
     * This value is required and cannot be null.
     */
    @NotNull(message = "Rating is Required")
    private Float rating;
}
