package com.acs560.FoodManagementSystem.views.Order;

import com.acs560.FoodManagementSystem.services.OrderService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * The {@link DeleteOrderFormView} class provides a user interface for deleting an order in the Food Management System.
 * It extends {@link VerticalLayout} and contains a form that allows users to input an order ID for deletion.
 */
@PermitAll
@Route(value = "delete-order-form")
@PageTitle("Delete Order | Food Management System")
public class DeleteOrderFormView extends VerticalLayout {

    private final OrderService orderService;

    private TextField orderIdField;        
    private Button deleteOrderButton;     
    private Button backButton;             

    /**
     * Constructs a new instance of {@link DeleteOrderFormView}.
     * Initializes the UI components, including the order ID field and buttons for deletion and navigation.
     *
     * @param orderService the service used to manage order-related operations
     */
    @Autowired
    public DeleteOrderFormView(OrderService orderService) {
        this.orderService = orderService;

        orderIdField = new TextField("Order ID (for deletion)");

        deleteOrderButton = new Button("Delete Order", event -> deleteOrder());
        backButton = new Button("Back to Orders",
                e -> getUI().ifPresent(ui -> ui.navigate(OrderListView.class)));

        FormLayout formLayout = new FormLayout();
        formLayout.add(orderIdField, deleteOrderButton, backButton);

        add(formLayout);
    }

    /**
     * Deletes the order specified by the order ID input in the order ID field.
     * Validates the input field and displays a notification based on the operation's success or failure.
     */
    private void deleteOrder() {
        if (validateFields()) {
            try {
                Integer orderId = Integer.parseInt(orderIdField.getValue());
                orderService.delete(orderId);
                Notification.show("Order deleted successfully!");
                clearFields();
                getUI().ifPresent(ui -> ui.navigate(OrderListView.class));
            } catch (Exception e) {
                Notification.show("Error deleting order: " + e.getMessage());
            }
        }
    }

    /**
     * Validates the input fields in the form.
     *
     * @return true if the order ID is valid and not empty, false otherwise
     */
    private boolean validateFields() {
        try {
            if (orderIdField.getValue().isEmpty()) {
                Notification.show("Order ID must not be empty.");
                return false;
            }
            Integer.parseInt(orderIdField.getValue());
            return true;
        } catch (NumberFormatException e) {
            Notification.show("Please ensure the Order ID is a valid number.");
            return false;
        }
    }

    /**
     * Clears the input fields in the form.
     */
    private void clearFields() {
        orderIdField.clear();
    }
}
