package com.acs560.FoodManagementSystem.services;

import java.util.List;
import java.util.Optional;

import com.acs560.FoodManagementSystem.entities.OrderEntity;
import com.acs560.FoodManagementSystem.models.Order;

/**
 * Service interface for managing order-related operations.
 * <p>
 * This interface provides methods for retrieving and managing order information
 * based on various criteria, such as order ID, cost, customer ID, restaurant ID,
 * and customer rating.
 * </p>
 */
public interface OrderService {

    /**
     * Retrieves a list of all orders.
     *
     * @return a list of all {@link OrderEntity} objects
     */
    List<OrderEntity> getAll();

    /**
     * Retrieves an order by its unique order ID.
     *
     * @param orderId the ID of the order to retrieve
     * @return an {@link Optional} containing the {@link OrderEntity} if found, or
     *         an empty Optional if not found
     */
    Optional<OrderEntity> getByOrderId(Integer orderId);

    /**
     * Retrieves a list of orders with a specific cost.
     *
     * @param costOfOrder the cost of the orders to find
     * @return a list of {@link OrderEntity} objects matching the specified cost
     */
    List<OrderEntity> getByCostOfOrder(float costOfOrder);

    /**
     * Retrieves a list of orders placed on a specific day of the week.
     *
     * @param dayOfTheWeek the day of the week to filter orders (e.g., "Monday", "Tuesday")
     * @return a list of {@link OrderEntity} objects placed on the specified day
     */
    List<OrderEntity> getByDayOfTheWeek(String dayOfTheWeek);

    /**
     * Retrieves a list of orders associated with a specific customer ID.
     *
     * @param customerId the ID of the customer whose orders are to be found
     * @return a list of {@link OrderEntity} objects associated with the specified customer
     */
    List<OrderEntity> getByCustomer_CustomerId(Integer customerId);

    /**
     * Retrieves a list of orders associated with a specific restaurant ID.
     *
     * @param restaurantId the ID of the restaurant whose orders are to be found
     * @return a list of {@link OrderEntity} objects associated with the specified restaurant
     */
    List<OrderEntity> getByRestaurant_RestaurantId(Integer restaurantId);

    /**
     * Adds a new order to the system.
     *
     * @param order               the {@link Order} object containing order details
     * @param restaurantName      the name of the restaurant associated with the order
     * @param foodPreparationTime the time required for food preparation
     * @param deliveryTime        the time required for delivery
     * @param customerRating      the customer's rating for the order
     */
    void addOrder(Order order, String restaurantName, Integer foodPreparationTime, Integer deliveryTime,
                  float customerRating);

    /**
     * Updates an existing order with new information.
     *
     * @param orderId             the ID of the order to update
     * @param updatedOrder        the {@link Order} object containing updated order details
     * @param restaurantName      the updated name of the restaurant associated with the order
     * @param foodPreparationTime the updated time required for food preparation
     * @param deliveryTime        the updated time required for delivery
     * @param customerRating      the updated customer's rating for the order
     */
    void updateOrder(Integer orderId, Order updatedOrder, String restaurantName, Integer foodPreparationTime,
	        Integer deliveryTime, Float customerRating);

    /**
     * Deletes an order from the system by its ID.
     *
     * @param orderId the ID of the order to delete
     */
    void delete(Integer orderId);

    /**
     * Retrieves a list of orders where the customer rating falls within a specified range.
     *
     * @param minRating the minimum rating in the range
     * @param maxRating the maximum rating in the range
     * @return a list of {@link OrderEntity} objects with a customer rating between the specified minimum and maximum values
     */
    List<OrderEntity> getByCustomerRatingRange(float minRating, float maxRating);
    
    /**
     * Retrieves a list of orders for a specific customer based on their customer ID.
     *
     * This method fetches the order history for the customer identified by the given 
     * customer ID. The order records are returned as a list of {@link OrderEntity} objects.
     *
     * @param customerId The unique ID of the customer whose order history is to be retrieved.
     * @return A list of {@link OrderEntity} objects representing the customer's order history.
     *         Returns an empty list if no orders are found for the specified customer.
     */
    List<OrderEntity> getOrderHistoryByCustomerId(Integer customerId);

}
