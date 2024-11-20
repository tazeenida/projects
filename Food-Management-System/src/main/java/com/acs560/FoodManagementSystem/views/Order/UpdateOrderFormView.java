package com.acs560.FoodManagementSystem.views.Order;

import com.acs560.FoodManagementSystem.models.Order;
import com.acs560.FoodManagementSystem.services.OrderService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * The {@link UpdateOrderFormView} class provides a user interface for updating
 * an existing order. It allows users to modify order details including cost,
 * day of the week, restaurant information, preparation time, delivery time, and
 * customer rating.
 * <p>
 * This view includes a form for inputting new values and a button to update the
 * order. On success, the user is navigated back to the list of orders.
 * </p>
 */
@PermitAll
@Route(value = "update-order-form")
@PageTitle("Update Order Form | Food Management System")
public class UpdateOrderFormView extends VerticalLayout {

    private final OrderService orderService;

    private TextField orderIdField;
    private TextField costOfOrderField;
    private ComboBox<String> dayOfTheWeekField;
    private TextField restaurantNameField;
    private IntegerField foodPreparationTimeField;
    private IntegerField deliveryTimeField;
    private TextField customerRatingField;
    private Button updateOrderButton;

    /**
     * Constructs a new instance of {@link UpdateOrderFormView}.
     * 
     * @param orderService the service used to update order details
     */
    @Autowired
    public UpdateOrderFormView(OrderService orderService) {
        this.orderService = orderService;

        orderIdField = new TextField("Order ID (for update)");
        orderIdField.setEnabled(true);
        costOfOrderField = new TextField("Cost of Order");
        dayOfTheWeekField = new ComboBox<>("Day of the Week");
        dayOfTheWeekField.setItems("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");
        restaurantNameField = new TextField("Restaurant Name");
        foodPreparationTimeField = new IntegerField("Food Preparation Time (minutes)");
        deliveryTimeField = new IntegerField("Delivery Time (minutes)");
        customerRatingField = new TextField("Customer Rating (0-5)");

        updateOrderButton = new Button("Update Order", event -> updateOrder());
        Button backButton = new Button("Back to Orders", e -> getUI().ifPresent(ui -> ui.navigate(OrderListView.class)));

        FormLayout formLayout = new FormLayout();
        formLayout.add(orderIdField, costOfOrderField, dayOfTheWeekField, restaurantNameField, foodPreparationTimeField,
                deliveryTimeField, customerRatingField, updateOrderButton, backButton);

        add(formLayout);
    }

    /**
     * Updates the order with the new information provided by the user.
     * 
     * @throws NumberFormatException if invalid data is entered
     */
    private void updateOrder() {
        if (validateFields(true)) {
            try {
                Integer orderId = Integer.parseInt(orderIdField.getValue());
                Order updatedOrder = new Order();

                String costOfOrderValue = costOfOrderField.getValue();
                if (!costOfOrderValue.isEmpty()) {
                    updatedOrder.setCostOfOrder(Float.parseFloat(costOfOrderValue));
                }

                String dayOfTheWeekValue = dayOfTheWeekField.getValue();
                if (dayOfTheWeekValue != null && !dayOfTheWeekValue.isEmpty()) {
                    updatedOrder.setDayOfTheWeek(dayOfTheWeekValue);
                }

                String restaurantName = restaurantNameField.getValue();
                Integer foodPreparationTime = foodPreparationTimeField.getValue();
                Integer deliveryTime = deliveryTimeField.getValue();
                String customerRatingValue = customerRatingField.getValue();

                Float customerRating = null;
                if (customerRatingValue != null && !customerRatingValue.isEmpty()) {
                    customerRating = Float.parseFloat(customerRatingValue);
                }

                orderService.updateOrder(orderId, updatedOrder, restaurantName, foodPreparationTime, deliveryTime, customerRating);
                Notification.show("Order updated successfully!");
                clearFields();
                getUI().ifPresent(ui -> ui.navigate(OrderListView.class));
            } catch (Exception e) {
                Notification.show("Error updating order: " + e.getMessage());
            }
        }
    }

    /**
     * Validates the order ID field to ensure it contains a valid integer.
     * 
     * @param isUpdate whether the operation is an update
     * @return true if the fields are valid, false otherwise
     */
    private boolean validateFields(boolean isUpdate) {
        try {
            if (isUpdate && !orderIdField.getValue().isEmpty()) {
                Integer.parseInt(orderIdField.getValue());
            }
            return true;
        } catch (NumberFormatException e) {
            Notification.show("Please ensure fields have valid numeric values: " + e.getMessage());
            return false;
        }
    }

    /**
     * Clears all input fields in the form.
     */
    private void clearFields() {
        orderIdField.clear();
        costOfOrderField.clear();
        dayOfTheWeekField.clear();
        restaurantNameField.clear();
        foodPreparationTimeField.clear();
        deliveryTimeField.clear();
        customerRatingField.clear();
    }
}
