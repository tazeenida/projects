package com.acs560.FoodManagementSystem.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * Represents a customer entity in the food management system.
 */
@Entity
@Table(name = "Customers")
@Data
@NoArgsConstructor
public class CustomerEntity {

    /**
     * Unique identifier for the customer.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    /**
     * Customer's rating based on feedback or performance.
     */
    @NotNull
    private float rating;
}
