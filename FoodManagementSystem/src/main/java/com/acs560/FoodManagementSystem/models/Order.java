package com.acs560.FoodManagementSystem.models;

import com.acs560.FoodManagementSystem.entities.CustomerEntity;
import com.acs560.FoodManagementSystem.entities.RestaurantEntity;

import lombok.*;

/**
 * Represents an order in the food management system.
 */
@Data
@NoArgsConstructor
public class Order {
    
    /**
     * Unique identifier for the order.
     */
    private Integer orderId;

    /**
     * Total cost of the order.
     */
    private float costOfOrder;

    /**
     * Day of the week when the order was placed.
     */
    private String dayOfTheWeek;

    /**
     * Customer who placed the order.
     */
    private CustomerEntity customerId;

    /**
     * Restaurant from which the order was made.
     */
    private RestaurantEntity restaurantId;
}
