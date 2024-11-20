package com.acs560.FoodManagementSystem.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acs560.FoodManagementSystem.entities.RestaurantEntity;
import com.acs560.FoodManagementSystem.repositories.RestaurantRepository;
import com.acs560.FoodManagementSystem.services.RestaurantService;

/**
 * Implementation of the {@link RestaurantService} interface for managing restaurant-related operations.
 * <p>
 * This class provides methods for retrieving restaurant information based on various criteria,
 * such as restaurant ID, name, food preparation time, and delivery time. It utilizes the
 * {@link RestaurantRepository} to access restaurant data from the underlying data source.
 * </p>
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {
    
    private final RestaurantRepository restaurantRepository;

    /**
     * Constructs a new instance of {@link RestaurantServiceImpl}.
     *
     * @param restaurantRepository the repository used to access restaurant data
     */
    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }
    
    /**
     * Retrieves a list of all restaurants.
     *
     * @return a list of all {@link RestaurantEntity} objects
     */
    @Override
    public List<RestaurantEntity> getAll() {
        List<RestaurantEntity> restaurantList = new ArrayList<>();
        restaurantRepository.findAll().forEach(restaurantList::add);
        return restaurantList;
    }

    /**
     * Retrieves a restaurant by its unique restaurant ID.
     *
     * @param restaurantId the ID of the restaurant to retrieve
     * @return an {@link Optional} containing the {@link RestaurantEntity} if found,
     *         or an empty Optional if not found
     */
    @Override
    public Optional<RestaurantEntity> getByRestaurantId(Integer restaurantId) {
        return Optional.ofNullable(restaurantRepository.findByRestaurantId(restaurantId));
    }

    /**
     * Retrieves a list of restaurants with a specific name.
     *
     * @param restaurantName the name of the restaurant to filter
     * @return a list of {@link RestaurantEntity} objects matching the specified name
     */
    @Override
    public List<RestaurantEntity> getByRestaurantName(String restaurantName) {
        return this.restaurantRepository.findByRestaurantName(restaurantName);
    }

    /**
     * Retrieves a list of restaurants with a specific food preparation time.
     *
     * @param foodPreparationTime the food preparation time to filter restaurants
     * @return a list of {@link RestaurantEntity} objects matching the specified food preparation time
     */
    @Override
    public List<RestaurantEntity> getByFoodPreparationTime(Integer foodPreparationTime) {
        return this.restaurantRepository.findByFoodPreparationTime(foodPreparationTime);
    }
    
    /**
     * Retrieves a list of restaurants with a specific delivery time.
     *
     * @param deliveryTime the delivery time to filter restaurants
     * @return a list of {@link RestaurantEntity} objects matching the specified delivery time
     */
    @Override
    public List<RestaurantEntity> getByDeliveryTime(Integer deliveryTime) {
        return this.restaurantRepository.findByDeliveryTime(deliveryTime);
    }
    
}
