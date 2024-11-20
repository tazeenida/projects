package com.acs560.FoodManagementSystem.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.acs560.FoodManagementSystem.entities.CustomerEntity;

/**
 * Repository interface for accessing and manipulating CustomerEntity data.
 */
public interface CustomerRepository extends CrudRepository<CustomerEntity, Integer> {

    /**
     * Find a customer by their unique customer ID.
     *
     * @param customerId the ID of the customer to find
     * @return the CustomerEntity with the specified ID, or null if not found
     */
    CustomerEntity findByCustomerId(Integer customerId);

    /**
     * Find all customers with a specific rating.
     *
     * @param rating the rating to filter customers
     * @return a list of CustomerEntity objects matching the specified rating
     */
    List<CustomerEntity> findByRating(float rating);
}
