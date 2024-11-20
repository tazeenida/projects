package com.acs560.FoodManagementSystem.views.Order;

import com.acs560.FoodManagementSystem.entities.OrderEntity;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;

/**
 * The {@link OrderDetailView} class represents a form for displaying the details of an order.
 * It extends {@link FormLayout} and utilizes various text fields to present order data.
 */
public class OrderDetailView extends FormLayout {

    private static final long serialVersionUID = 476310807171214015L;

    private final TextField orderId = new TextField("Order ID");
    private final TextField costOfOrder = new TextField("Cost of Order");
    private final TextField dayOfTheWeek = new TextField("Day of the Week");
    private final TextField customerId = new TextField("Customer ID"); 
    private final TextField restaurantId = new TextField("Restaurant ID");

    private final Binder<OrderEntity> binder = new BeanValidationBinder<>(OrderEntity.class);

    /**
     * Constructs a new instance of {@link OrderDetailView}.
     * Initializes the form fields and binds them to the corresponding fields in {@link OrderEntity}.
     */
    public OrderDetailView() {
        addClassName("order-detail");

        binder.bindInstanceFields(this);

        orderId.setReadOnly(true);
        costOfOrder.setReadOnly(true);
        dayOfTheWeek.setReadOnly(true);
        customerId.setReadOnly(true);
        restaurantId.setReadOnly(true);

        add(orderId, costOfOrder, dayOfTheWeek, customerId, restaurantId);  
        setWidth("25em");
    }

    /**
     * Updates the form with order data for display purposes.
     *
     * @param order the order to be displayed, represented as an {@link OrderEntity}
     */
    public void setOrder(OrderEntity order) {
        binder.readBean(order); 
    }

    /**
     * Clears the form fields when no order is selected.
     * This method resets the binder and clears any displayed data.
     */
    public void clear() {
        binder.readBean(null); 
    }
}
