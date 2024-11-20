package com.acs560.FoodManagementSystem.services;

import java.util.List;
import java.util.Optional;

import com.acs560.FoodManagementSystem.entities.CustomerEntity;

/**
 * Service interface for managing customer-related operations.
 * <p>
 * This interface provides methods for retrieving customer information
 * based on various criteria, such as customer ID and rating.
 * </p>
 */
public interface CustomerService {
	
	/**
     * Retrieves a list of all customers.
     *
     * @return a list of all {@link CustomerEntity} objects
     */
    List<CustomerEntity> getAll();

    /**
     * Retrieves a customer by their unique customer ID.
     *
     * @param customerId the ID of the customer to retrieve
     * @return an {@link Optional} containing the {@link CustomerEntity} if found,
     *         or an empty Optional if not found
     */
    Optional<CustomerEntity> getByCustomerId(Integer customerId);

    /**
     * Retrieves a list of customers with a specific rating.
     *
     * @param rating the rating to filter customers
     * @return a list of {@link CustomerEntity} objects matching the specified rating
     */
    List<CustomerEntity> getByRating(float rating);
    
}
