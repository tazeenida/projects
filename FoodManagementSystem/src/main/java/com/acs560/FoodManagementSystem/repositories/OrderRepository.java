package com.acs560.FoodManagementSystem.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.acs560.FoodManagementSystem.entities.OrderEntity;

/**
 * Repository interface for accessing and manipulating {@link OrderEntity} data.
 * Extends {@link CrudRepository} to provide basic CRUD operations and includes
 * additional query methods for specific attributes.
 */
public interface OrderRepository extends CrudRepository<OrderEntity, Integer> {

    /**
     * Find an order by its unique order ID.
     *
     * @param orderId the ID of the order to find
     * @return the {@link OrderEntity} with the specified ID, or null if not found
     */
    OrderEntity findByOrderId(Integer orderId);

    /**
     * Find all orders with a specific cost.
     *
     * @param costOfOrder the cost of the orders to find
     * @return a list of {@link OrderEntity} objects matching the specified cost
     */
    List<OrderEntity> findByCostOfOrder(float costOfOrder);

    /**
     * Find all orders placed on a specific day of the week.
     *
     * @param dayOfTheWeek the day of the week to filter orders (e.g., "Monday", "Tuesday")
     * @return a list of {@link OrderEntity} objects placed on the specified day
     */
    List<OrderEntity> findByDayOfTheWeek(String dayOfTheWeek);

    /**
     * Find all orders associated with a specific customer ID.
     *
     * @param customerId the ID of the customer whose orders are to be found
     * @return a list of {@link OrderEntity} objects associated with the specified customer
     */
    List<OrderEntity> findByCustomer_CustomerId(Integer customerId);

    /**
     * Find all orders associated with a specific restaurant ID.
     *
     * @param restaurantId the ID of the restaurant whose orders are to be found
     * @return a list of {@link OrderEntity} objects associated with the specified restaurant
     */
    List<OrderEntity> findByRestaurant_RestaurantId(Integer restaurantId);

    /**
     * Find all orders where the customer rating falls within a specified range.
     *
     * @param minRating the minimum rating in the range
     * @param maxRating the maximum rating in the range
     * @return a list of {@link OrderEntity} objects with a customer rating between the specified minimum and maximum values
     */
    List<OrderEntity> findByCustomer_RatingBetween(float minRating, float maxRating);
}
