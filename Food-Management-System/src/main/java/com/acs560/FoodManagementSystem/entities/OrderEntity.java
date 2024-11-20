package com.acs560.FoodManagementSystem.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * Represents an order entity in the food management system.
 */
@Entity
@Table(name = "Orders")
@Data
@NoArgsConstructor
public class OrderEntity {
    
    /**
     * Unique identifier for the order.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
     * This field cannot be null.
     */
    @ManyToOne
    @NotNull
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    /**
     * Restaurant from which the order was placed.
     * This field cannot be null.
     */
    @ManyToOne
    @NotNull
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant; 
}