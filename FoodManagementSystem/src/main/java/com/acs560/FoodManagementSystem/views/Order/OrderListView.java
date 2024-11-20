package com.acs560.FoodManagementSystem.views.Order;

import com.acs560.FoodManagementSystem.entities.OrderEntity;
import com.acs560.FoodManagementSystem.services.OrderService;
import com.acs560.FoodManagementSystem.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import jakarta.annotation.security.PermitAll;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Scope;

/**
 * The {@link OrderListView} class provides a user interface for displaying and managing orders.
 * It extends {@link VerticalLayout} and includes functionality for filtering and listing orders.
 */
@SpringComponent
@PermitAll
@Route(value = "", layout = MainLayout.class)
@PageTitle("Orders | Food Management System")
@Scope("prototype")
public class OrderListView extends VerticalLayout {

    private static final long serialVersionUID = 1L;

    private final OrderService orderService;
    private final Grid<OrderEntity> grid;
    private final TextField filterText;
    private final TextField minRatingField;
    private final TextField maxRatingField;
    private final Button addOrderButton;
    private final Button updateOrderButton;
    private final Button deleteOrderButton;

    /**
     * Constructs a new instance of {@link OrderListView}.
     * Initializes the layout, grid for displaying orders, and the filter text field.
     *
     * @param orderService the service to manage order data
     */
    public OrderListView(OrderService orderService) {
        this.orderService = orderService;

        addClassName("list-view");
        setSizeFull();

        grid = createGrid();
        filterText = createFilter();
        minRatingField = createMinRatingFilter(); // Create min rating filter
        maxRatingField = createMaxRatingFilter(); // Create max rating filter
        addOrderButton = new Button("Add Order", event -> navigateToAddOrder());
        updateOrderButton = new Button("Update Order", event -> navigateToUpdateOrder());
        deleteOrderButton = new Button("Delete Order", event -> navigateToDeleteOrder());

        add(createToolbar(filterText, minRatingField, maxRatingField, addOrderButton, updateOrderButton, deleteOrderButton), grid);
        updateGrid(); 
    }

    /**
     * Creates and configures a grid for displaying orders.
     *
     * @return a configured {@link Grid} instance for {@link OrderEntity}
     */
    private Grid<OrderEntity> createGrid() {
        Grid<OrderEntity> grid = new Grid<>(OrderEntity.class);
        grid.addClassNames("order-grid");
        grid.setSizeFull();
        
        grid.setColumns("orderId", "costOfOrder", "dayOfTheWeek", "customer", "restaurant");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event -> updateOrderButton.setEnabled(event.getValue() != null));

        return grid;
    }

    /**
     * Creates a text field for filtering orders based on the order ID.
     *
     * @return a configured {@link TextField} instance for filtering
     */
    private TextField createFilter() {
        TextField filterText = new TextField();
        filterText.setValueChangeTimeout(1000);
        filterText.setPlaceholder("Filter by order ID...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateGrid());

        return filterText;
    }

    /**
     * Creates a text field for filtering orders based on the minimum customer rating.
     *
     * @return a configured {@link TextField} instance for min rating filtering
     */
    private TextField createMinRatingFilter() {
        TextField minRatingField = new TextField();
        minRatingField.setValueChangeTimeout(1000);
        minRatingField.setPlaceholder("Min rating...");
        minRatingField.setClearButtonVisible(true);
        minRatingField.setValueChangeMode(ValueChangeMode.LAZY);
        minRatingField.addValueChangeListener(e -> updateGrid());

        return minRatingField;
    }

    /**
     * Creates a text field for filtering orders based on the maximum customer rating.
     *
     * @return a configured {@link TextField} instance for max rating filtering
     */
    private TextField createMaxRatingFilter() {
        TextField maxRatingField = new TextField();
        maxRatingField.setValueChangeTimeout(1000);
        maxRatingField.setPlaceholder("Max rating...");
        maxRatingField.setClearButtonVisible(true);
        maxRatingField.setValueChangeMode(ValueChangeMode.LAZY);
        maxRatingField.addValueChangeListener(e -> updateGrid());

        return maxRatingField;
    }

    /**
     * Creates a toolbar containing the filter text field and action buttons.
     *
     * @param filterText the text field used for filtering orders by ID
     * @param minRatingField the text field used for filtering by minimum rating
     * @param maxRatingField the text field used for filtering by maximum rating
     * @param addOrderButton the button for adding new orders
     * @param updateOrderButton the button for updating existing orders
     * @param deleteOrderButton the button for deleting orders
     * @return a {@link HorizontalLayout} containing the toolbar components
     */
    private Component createToolbar(TextField filterText, TextField minRatingField, TextField maxRatingField,
                                    Button addOrderButton, Button updateOrderButton, Button deleteOrderButton) {
        var toolbar = new HorizontalLayout(filterText, minRatingField, maxRatingField, addOrderButton, updateOrderButton, deleteOrderButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    /**
     * Updates the grid with orders based on the current filter text and rating range.
     * It retrieves orders from the {@link OrderService} and sets them in the grid.
     */
    private void updateGrid() {
        String filter = filterText.getValue();
        String minRating = minRatingField.getValue();
        String maxRating = maxRatingField.getValue();
        List<OrderEntity> orders;

        if (filter == null || filter.isEmpty()) {
            orders = orderService.getAll();
        } else {
            try {
                Integer orderId = Integer.parseInt(filter); 
                Optional<OrderEntity> orderOpt = orderService.getByOrderId(orderId);
                orders = orderOpt.map(List::of).orElseGet(List::of); 
            } catch (NumberFormatException e) {
                orders = List.of();
            }
        }

        if ((minRating != null && !minRating.isEmpty()) && (maxRating != null && !maxRating.isEmpty())) {
            try {
                float min = Float.parseFloat(minRating);
                float max = Float.parseFloat(maxRating);
                orders = orderService.getByCustomerRatingRange(min, max);
            } catch (NumberFormatException e) {
                Notification.show("Invalid rating range.");
                orders = List.of();
            }
        }

        grid.setItems(orders); 
    }

    /**
     * Navigates to the Add Order form.
     */
    private void navigateToAddOrder() {
        getUI().ifPresent(ui -> ui.navigate("add-order-form"));
    }

    /**
     * Navigates to the Update Order form.
     */
    private void navigateToUpdateOrder() {
        getUI().ifPresent(ui -> ui.navigate("update-order-form"));
    }

    /**
     * Navigates to the Delete Order form.
     */
    private void navigateToDeleteOrder() {
        getUI().ifPresent(ui -> ui.navigate("delete-order-form"));
    }
}
