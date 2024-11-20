package com.acs560.FoodManagementSystem.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a request object for creating or updating an order.
 * This class contains the necessary information for an order,
 * including cost, day of the week, and associated customer and restaurant IDs.
 */
@Data
@NoArgsConstructor
public class OrderRequest {

    /**
     * The cost of the order.
     * This value is required and cannot be null.
     */
    @NotNull(message="Cost Of Order is Required")
    private Float costOfOrder;

    /**
     * The day of the week when the order is placed.
     * This value is required and cannot be empty.
     */
    @NotEmpty(message="Day Of The Week is Required")
    private String dayOfTheWeek;

    /**
     * The unique identifier of the customer placing the order.
     * This value is required and cannot be null.
     */
    @NotNull(message="Customer Id is Required")
    private Integer customerId;

    /**
     * The unique identifier of the restaurant fulfilling the order.
     * This value is required and cannot be null.
     */
    @NotNull(message="Restaurant Id is Required")
    private Integer restaurantId;
}
