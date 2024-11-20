package com.acs560.FoodManagementSystem.views.Restaurant;

import com.acs560.FoodManagementSystem.models.Restaurant;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;

import lombok.Getter;

/**
 * The {@link RestaurantFormView} class provides a form layout for displaying
 * and updating restaurant details.
 * It extends {@link FormLayout} and includes fields for the restaurant's name,
 * food preparation time, and delivery time.
 */
public class RestaurantFormView extends FormLayout {

    private final TextField restaurantName = new TextField("Restaurant Name");
    private final TextField foodPreparationTime = new TextField("Food Preparation Time (minutes)");
    private final TextField deliveryTime = new TextField("Delivery Time (minutes)");

    private Restaurant restaurant;

    /**
     * Constructs a new instance of {@link RestaurantFormView}.
     * Initializes the form layout with fields for restaurant details.
     */
    public RestaurantFormView() {
        add(restaurantName, foodPreparationTime, deliveryTime);
        setWidth("25em");
    }

    /**
     * Updates the form with the provided restaurant details.
     * If the restaurant is not null, the form fields are populated with
     * the restaurant's details. If the restaurant is null, the fields are cleared.
     *
     * @param restaurant the restaurant to display in the form
     */
    public void update(Restaurant restaurant) {
        this.restaurant = restaurant;

        if (restaurant != null) {
            restaurantName.setValue(restaurant.getRestaurantName());
            foodPreparationTime.setValue(restaurant.getFoodPreparationTime() != null ? restaurant.getFoodPreparationTime().toString() : "");
            deliveryTime.setValue(restaurant.getDeliveryTime() != null ? restaurant.getDeliveryTime().toString() : "");
        } else {
            restaurantName.clear();
            foodPreparationTime.clear();
            deliveryTime.clear();
        }
    }
}
