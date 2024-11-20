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
 * A view for adding and updating orders in the Food Management System.
 * This class provides a user interface form to input order details such as 
 * cost, day of the week, restaurant name, food preparation time, delivery time, 
 * and customer rating. It utilizes Vaadin components for the layout and interactivity.
 */
@PermitAll
@Route(value = "add-order-form")
@PageTitle("Order Form | Food Management System")
public class AddOrderFormView extends VerticalLayout {

    private final OrderService orderService;

    private TextField orderIdField;            
    private TextField costOfOrderField;        
    private ComboBox<String> dayOfTheWeekField; 
    private TextField restaurantNameField;     
    private IntegerField foodPreparationTimeField; 
    private IntegerField deliveryTimeField;    
    private TextField customerRatingField;     
    private Button addOrderButton;              
    private Button updateOrderButton;           

    /**
     * Constructs an instance of AddOrderFormView.
     * Initializes the UI components and sets up the layout.
     *
     * @param orderService the service used to manage orders
     */
    @Autowired
    public AddOrderFormView(OrderService orderService) {
        this.orderService = orderService;

        orderIdField = new TextField("Order ID (for update)");
        orderIdField.setEnabled(false);
        costOfOrderField = new TextField("Cost of Order");
        dayOfTheWeekField = new ComboBox<>("Day of the Week");
        dayOfTheWeekField.setItems("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");
        restaurantNameField = new TextField("Restaurant Name");
        foodPreparationTimeField = new IntegerField("Food Preparation Time (minutes)");
        deliveryTimeField = new IntegerField("Delivery Time (minutes)");
        customerRatingField = new TextField("Customer Rating (0-5)");

        addOrderButton = new Button("Add Order", event -> addOrder());
        Button backButton = new Button("Back to Orders", e -> getUI().ifPresent(ui -> ui.navigate(OrderListView.class)));

        FormLayout formLayout = new FormLayout();
        formLayout.add(orderIdField, costOfOrderField, dayOfTheWeekField, restaurantNameField,
                       foodPreparationTimeField, deliveryTimeField, customerRatingField,
                       addOrderButton, backButton);

        add(formLayout);
    }

    /**
     * Adds a new order using the input fields' values.
     * Validates the fields and shows notifications based on the operation's success or failure.
     */
    private void addOrder() {
        if (validateFields(false)) { 
            try {
                Order order = new Order();
                order.setCostOfOrder(Float.parseFloat(costOfOrderField.getValue()));
                order.setDayOfTheWeek(dayOfTheWeekField.getValue());

                String restaurantName = restaurantNameField.getValue();
                Integer foodPreparationTime = foodPreparationTimeField.getValue();
                Integer deliveryTime = deliveryTimeField.getValue();
                float customerRating = Float.parseFloat(customerRatingField.getValue());

                orderService.addOrder(order, restaurantName, foodPreparationTime, deliveryTime, customerRating);
                Notification.show("Order added successfully!");
                clearFields();
                getUI().ifPresent(ui -> ui.navigate(OrderListView.class));
            } catch (Exception e) {
                Notification.show("Error adding order: " + e.getMessage());
            }
        }
    }

    /**
     * Validates the input fields for the order form.
     *
     * @param isUpdate a boolean indicating whether the validation is for an update operation
     * @return true if all fields are valid, false otherwise
     */
    private boolean validateFields(boolean isUpdate) {
        try {
            if (isUpdate && !orderIdField.getValue().isEmpty()) {
                Integer.parseInt(orderIdField.getValue());
            }

            if (foodPreparationTimeField.getValue() == null) {
                throw new NumberFormatException("Food Preparation Time is required.");
            }
            if (deliveryTimeField.getValue() == null) {
                throw new NumberFormatException("Delivery Time is required.");
            }

            if (!costOfOrderField.getValue().isEmpty()) {
                Float.parseFloat(costOfOrderField.getValue());
            }
            if (!customerRatingField.getValue().isEmpty()) {
                float rating = Float.parseFloat(customerRatingField.getValue());
                if (rating < 0 || rating > 5) {
                    throw new NumberFormatException("Customer Rating must be between 0 and 5.");
                }
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
