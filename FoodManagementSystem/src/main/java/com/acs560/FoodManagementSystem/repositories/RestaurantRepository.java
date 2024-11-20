package com.acs560.FoodManagementSystem.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.acs560.FoodManagementSystem.entities.RestaurantEntity;

/**
 * Repository interface for accessing and manipulating RestaurantEntity data.
 */
public interface RestaurantRepository extends CrudRepository<RestaurantEntity, Integer> {

    /**
     * Find a restaurant by its unique restaurant ID.
     *
     * @param restaurantId the ID of the restaurant to find
     * @return the RestaurantEntity with the specified ID, or null if not found
     */
    RestaurantEntity findByRestaurantId(Integer restaurantId);

    /**
     * Find all restaurants with a specific name.
     *
     * @param restaurantName the name of the restaurant to find
     * @return a list of RestaurantEntity objects matching the specified name
     */
    List<RestaurantEntity> findByRestaurantName(String restaurantName);

    /**
     * Find all restaurants with a specific food preparation time.
     *
     * @param foodPreparationTime the food preparation time to filter restaurants
     * @return a list of RestaurantEntity objects matching the specified preparation time
     */
    List<RestaurantEntity> findByFoodPreparationTime(Integer foodPreparationTime);

    /**
     * Find all restaurants with a specific delivery time.
     *
     * @param deliveryTime the delivery time to filter restaurants
     * @return a list of RestaurantEntity objects matching the specified delivery time
     */
    List<RestaurantEntity> findByDeliveryTime(Integer deliveryTime);
}
