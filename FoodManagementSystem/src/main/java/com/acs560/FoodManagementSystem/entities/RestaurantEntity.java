package com.acs560.FoodManagementSystem.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * Represents a restaurant entity in the food management system.
 */
@Entity
@Table(name = "Restaurants")
@Data
@NoArgsConstructor
public class RestaurantEntity {

    /**
     * Unique identifier for the restaurant.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer restaurantId;

    /**
     * Name of the restaurant.
     * This should not be null.
     */
    @NotNull
    private String restaurantName;

    /**
     * Time taken to prepare food at the restaurant, in minutes.
     */
    private Integer foodPreparationTime;

    /**
     * Time taken for delivery from the restaurant, in minutes.
     */
    private Integer deliveryTime;
}
