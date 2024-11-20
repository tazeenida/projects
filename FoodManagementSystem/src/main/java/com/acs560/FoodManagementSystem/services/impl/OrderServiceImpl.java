package com.acs560.FoodManagementSystem.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acs560.FoodManagementSystem.entities.CustomerEntity;
import com.acs560.FoodManagementSystem.entities.OrderEntity;
import com.acs560.FoodManagementSystem.entities.RestaurantEntity;
import com.acs560.FoodManagementSystem.models.Order;
import com.acs560.FoodManagementSystem.repositories.CustomerRepository;
import com.acs560.FoodManagementSystem.repositories.OrderRepository;
import com.acs560.FoodManagementSystem.repositories.RestaurantRepository;
import com.acs560.FoodManagementSystem.services.OrderService;

import jakarta.transaction.Transactional;

/**
 * Implementation of the {@link OrderService} interface for managing
 * order-related operations.
 * <p>
 * This class provides methods for retrieving order information based on various
 * criteria, such as order ID, cost, customer, and restaurant. It utilizes the
 * {@link OrderRepository} to access order data from the underlying data source.
 * </p>
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;
    private final CustomerRepository customerRepository;

    /**
     * Constructs a new instance of {@link OrderServiceImpl}.
     * 
     * @param orderRepository the repository used to access order data
     * @param restaurantRepository the repository used to access restaurant data
     * @param customerRepository the repository used to access customer data
     */
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, RestaurantRepository restaurantRepository, 
                            CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.restaurantRepository = restaurantRepository;
        this.customerRepository = customerRepository;
    }

    /**
     * Retrieves a list of all orders.
     *
     * @return a list of all {@link OrderEntity} objects
     */
    @Override
    public List<OrderEntity> getAll() {
        List<OrderEntity> orderList = new ArrayList<>();
        orderRepository.findAll().forEach(orderList::add);
        return orderList;
    }

    /**
     * Retrieves an order by its unique order ID.
     *
     * @param orderId the ID of the order to retrieve
     * @return an {@link Optional} containing the {@link OrderEntity} if found, or
     *         an empty Optional if not found
     */
    @Override
    public Optional<OrderEntity> getByOrderId(Integer orderId) {
        return Optional.ofNullable(orderRepository.findByOrderId(orderId));
    }

    /**
     * Retrieves a list of orders with a specific cost.
     *
     * @param costOfOrder the cost of the order to filter
     * @return a list of {@link OrderEntity} objects matching the specified cost
     */
    @Override
    public List<OrderEntity> getByCostOfOrder(float costOfOrder) {
        return orderRepository.findByCostOfOrder(costOfOrder);
    }

    /**
     * Retrieves a list of orders made on a specific day of the week.
     *
     * @param dayOfTheWeek the day of the week to filter orders
     * @return a list of {@link OrderEntity} objects matching the specified day of
     *         the week
     */
    @Override
    public List<OrderEntity> getByDayOfTheWeek(String dayOfTheWeek) {
        return orderRepository.findByDayOfTheWeek(dayOfTheWeek);
    }

    /**
     * Retrieves a list of orders placed by a specific customer.
     *
     * @param customerId the ID of the customer whose orders to retrieve
     * @return a list of {@link OrderEntity} objects associated with the specified
     *         customer
     */
    @Override
    public List<OrderEntity> getByCustomer_CustomerId(Integer customerId) {
        return orderRepository.findByCustomer_CustomerId(customerId);
    }

    /**
     * Retrieves a list of orders associated with a specific restaurant.
     *
     * @param restaurantId the ID of the restaurant whose orders to retrieve
     * @return a list of {@link OrderEntity} objects associated with the specified
     *         restaurant
     */
    @Override
    public List<OrderEntity> getByRestaurant_RestaurantId(Integer restaurantId) {
        return orderRepository.findByRestaurant_RestaurantId(restaurantId);
    }

    /**
     * Adds a new order, including associated customer and restaurant data.
     *
     * @param order the order to be added
     * @param restaurantName the name of the restaurant for the order
     * @param foodPreparationTime the food preparation time for the order
     * @param deliveryTime the delivery time for the order
     * @param customerRating the rating of the customer for the order
     */
    @Transactional
    public void addOrder(Order order, String restaurantName, Integer foodPreparationTime, Integer deliveryTime,
                         float customerRating) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setRating(customerRating);
        CustomerEntity savedCustomer = customerRepository.save(customerEntity);

        RestaurantEntity restaurantEntity = new RestaurantEntity();
        restaurantEntity.setRestaurantName(restaurantName);
        restaurantEntity.setFoodPreparationTime(foodPreparationTime);
        restaurantEntity.setDeliveryTime(deliveryTime);
        RestaurantEntity savedRestaurant = restaurantRepository.save(restaurantEntity);

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCostOfOrder(order.getCostOfOrder());
        orderEntity.setDayOfTheWeek(order.getDayOfTheWeek());
        orderEntity.setCustomer(savedCustomer);
        orderEntity.setRestaurant(savedRestaurant);

        orderRepository.save(orderEntity);
    }

    /**
     * Updates an existing order with new values, including associated customer
     * and restaurant data.
     * 
     * @param orderId the ID of the order to be updated
     * @param updatedOrder the updated order data
     * @param restaurantName the name of the restaurant for the order
     * @param foodPreparationTime the food preparation time for the order
     * @param deliveryTime the delivery time for the order
     * @param customerRating the new rating of the customer for the order
     */
    @Transactional
    public void updateOrder(Integer orderId, Order updatedOrder, String restaurantName, Integer foodPreparationTime,
                            Integer deliveryTime, Float customerRating) {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
        if (orderEntity == null) {
            throw new IllegalArgumentException("Order not found for ID: " + orderId);
        }

        if (updatedOrder.getCostOfOrder() > 0) {
            orderEntity.setCostOfOrder(updatedOrder.getCostOfOrder());
        }

        if (updatedOrder.getDayOfTheWeek() != null) {
            orderEntity.setDayOfTheWeek(updatedOrder.getDayOfTheWeek());
        }

        RestaurantEntity restaurantEntity = orderEntity.getRestaurant();
        if (restaurantEntity != null) {
            if (restaurantName != null) {
                restaurantEntity.setRestaurantName(restaurantName);
            }
            if (foodPreparationTime != null) {
                restaurantEntity.setFoodPreparationTime(foodPreparationTime);
            }
            if (deliveryTime != null) {
                restaurantEntity.setDeliveryTime(deliveryTime);
            }
        }

        CustomerEntity customerEntity = orderEntity.getCustomer();
        if (customerEntity != null && customerRating != null) {
            customerEntity.setRating(customerRating);
        }

        orderRepository.save(orderEntity);
        if (restaurantEntity != null) {
            restaurantRepository.save(restaurantEntity);
        }
        if (customerEntity != null) {
            customerRepository.save(customerEntity);
        }
    }

    /**
     * Deletes an order by its ID, along with the associated customer and
     * restaurant data.
     *
     * @param orderId the ID of the order to be deleted
     */
    @Override
    @Transactional
    public void delete(Integer orderId) {
        Optional<OrderEntity> orderOpt = orderRepository.findById(orderId);
        if (orderOpt.isPresent()) {
            OrderEntity order = orderOpt.get();

            orderRepository.delete(order);

            CustomerEntity customer = order.getCustomer();
            RestaurantEntity restaurant = order.getRestaurant();

            if (customer != null) {
                customerRepository.delete(customer);
            }
            if (restaurant != null) {
                restaurantRepository.delete(restaurant);
            }
        }
    }

    /**
     * Retrieves a list of orders placed by customers within a specified rating
     * range.
     *
     * @param minRating the minimum customer rating
     * @param maxRating the maximum customer rating
     * @return a list of {@link OrderEntity} objects within the specified rating range
     */
    @Override
    public List<OrderEntity> getByCustomerRatingRange(float minRating, float maxRating) {
        return orderRepository.findByCustomer_RatingBetween(minRating, maxRating);
    }
    
    /**
     * Retrieves a list of orders for a given customer by their customer ID.
     * 
     * This method queries the order repository to fetch all orders associated with 
     * the specified customer ID. The customer ID is used to find the related orders 
     * from the order records.
     * 
     * @param customerId The ID of the customer whose order history is to be retrieved.
     * @return A list of {@link OrderEntity} objects representing the customer's order history.
     *         If no orders are found, an empty list is returned.
     */
    public List<OrderEntity> getOrderHistoryByCustomerId(Integer customerId){
        return orderRepository.findByCustomer_CustomerId(customerId);
    }

}
