package com.acs560.FoodManagementSystem.models;

import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * Represents a restaurant in the food management system.
 */
@Data
@NoArgsConstructor
@ToString
public class Restaurant {
    
    /**
     * Unique identifier for the restaurant.
     */
    private Integer restaurantId;

    /**
     * Name of the restaurant. This field cannot be null.
     */
    @NotNull
    private String restaurantName;

    /**
     * Average time (in minutes) for food preparation at the restaurant.
     */
    private Integer foodPreparationTime;

    /**
     * Average time (in minutes) for delivery from the restaurant.
     */
    private Integer deliveryTime;
}
