package com.acs560.FoodManagementSystem.views.Order;

import com.acs560.FoodManagementSystem.entities.OrderEntity;
import com.acs560.FoodManagementSystem.services.OrderService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import com.vaadin.flow.router.PageTitle;
import com.acs560.FoodManagementSystem.views.MainLayout;

import java.util.List;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

/**
 * View for displaying the order history of a customer.
 */
@PermitAll
@Route(value = "order-history", layout = MainLayout.class)
@PageTitle("Order History | Food Management System")
public class OrderHistoryView extends VerticalLayout {

    private final OrderService orderService;
    private final Grid<OrderEntity> orderGrid;
    private final IntegerField customerIdField;
    private final Button fetchButton;

    /**
     * Constructs the OrderHistoryView with the provided order service.
     *
     * @param orderService The service used to fetch order data.
     */
    public OrderHistoryView(OrderService orderService) {
        this.orderService = orderService;

        H2 header = new H2("Customer Order History");
        add(header);

        customerIdField = new IntegerField("Customer ID");
        customerIdField.setPlaceholder("Enter Customer ID");
        customerIdField.setWidth("300px");

        fetchButton = new Button("Get Order History", event -> fetchOrderHistory());

        Div inputLayout = new Div(customerIdField, fetchButton);
        inputLayout.getStyle().set("display", "flex");
        inputLayout.getStyle().set("gap", "10px");
        add(inputLayout);

        orderGrid = new Grid<>(OrderEntity.class, false);
        orderGrid.addColumn(order -> order.getCustomer().getCustomerId())
                .setHeader("Customer ID");
        orderGrid.addColumn(order -> order.getRestaurant().getRestaurantName()).setHeader("Restaurant Name");
        orderGrid.addColumn(order -> order.getCustomer().getRating()).setHeader("Customer Rating");

        orderGrid.setWidthFull();
        orderGrid.setHeight("400px");

        add(orderGrid);

        setSpacing(true);
        setPadding(true);
        setAlignItems(Alignment.CENTER);
    }

    /**
     * Fetches the order history for the given customer ID and populates the grid.
     */
    private void fetchOrderHistory() {
        Integer customerId = customerIdField.getValue();
        if (customerId != null) {
            List<OrderEntity> orders = orderService.getOrderHistoryByCustomerId(customerId);
            if (orders != null && !orders.isEmpty()) {
                orderGrid.setItems(orders);
            } else {
                orderGrid.setItems();
                orderGrid.getElement().setText("No orders found for the given Customer ID.");
            }
        } else {
            orderGrid.setItems();
            orderGrid.getElement().setText("Please enter a valid Customer ID.");
        }
    }
}
