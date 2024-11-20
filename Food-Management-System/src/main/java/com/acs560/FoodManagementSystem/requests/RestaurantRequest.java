package com.acs560.FoodManagementSystem.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a request object for creating or updating a restaurant.
 * This class contains the necessary information about a restaurant,
 * including its name, food preparation time, and delivery time.
 */
@Data
@NoArgsConstructor
public class RestaurantRequest {

    /**
     * The name of the restaurant.
     * This value is required and cannot be empty.
     */
    @NotEmpty(message ="Restaurant Name is required")
    private String restaurantName;

    /**
     * The food preparation time in minutes.
     * This value is required and cannot be null.
     */
    @NotNull(message ="Food Preparation Time is required")
    private Integer foodPreparationTime;

    /**
     * The delivery time in minutes.
     * This value is required and cannot be null.
     */
    @NotNull(message ="Delivery Time is required")
    private Integer deliveryTime;
}
