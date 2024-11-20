package com.acs560.FoodManagementSystem.views.Customer; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import com.acs560.FoodManagementSystem.entities.CustomerEntity;
import com.acs560.FoodManagementSystem.services.CustomerService;
import com.acs560.FoodManagementSystem.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import jakarta.annotation.security.PermitAll;

import java.util.List;

/**
 * The {@link CustomerListView} class provides a user interface for managing and displaying a list of customers.
 * It extends {@link VerticalLayout} and utilizes a grid to present customer data, along with a filter 
 * for searching customers by their ID.
 */
@SpringComponent
@Scope("prototype")
@PermitAll
@Route(value = "customers", layout = MainLayout.class)
@PageTitle("Customers | Food Management System")
public class CustomerListView extends VerticalLayout {

    private static final long serialVersionUID = 1L;

    @Autowired
    private CustomerService customerService;

    private final Grid<CustomerEntity> grid;
    private final TextField filterText;

    /**
     * Constructs a new instance of {@link CustomerListView}.
     * Initializes the view components, including the customer grid and filter text field.
     *
     * @param customerService the service used to access customer data
     */
    public CustomerListView(CustomerService customerService) {
        this.customerService = customerService;

        addClassName("list-view");
        setSizeFull();

        grid = createGrid();
        filterText = createFilter();

        add(createToolbar(filterText), grid);
        updateGrid();
    }

    /**
     * Creates and configures the grid for displaying customer data.
     *
     * @return a configured {@link Grid} of {@link CustomerEntity} objects
     */
    private Grid<CustomerEntity> createGrid() {
        Grid<CustomerEntity> grid = new Grid<>(CustomerEntity.class);
        grid.addClassNames("customer-grid");
        grid.setSizeFull();
        grid.setColumns("customerId", "rating"); 
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        return grid;
    }

    /**
     * Creates and configures the filter text field for searching customers.
     *
     * @return a configured {@link TextField} for filtering customer data
     */
    private TextField createFilter() {
        TextField filterText = new TextField();
        filterText.setValueChangeTimeout(1000);
        filterText.setPlaceholder("Filter by customer ID...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateGrid());

        return filterText;
    }

    /**
     * Creates a toolbar containing the filter text field.
     *
     * @param filterText the filter text field to be added to the toolbar
     * @return a {@link Component} representing the toolbar
     */
    private Component createToolbar(TextField filterText) {
        var toolbar = new HorizontalLayout(filterText);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    /**
     * Updates the grid with customer data based on the filter text.
     * If the filter is empty, all customers are displayed. 
     * If a valid customer ID is provided, it fetches the corresponding customer.
     */
    private void updateGrid() {
        String filter = filterText.getValue();
        List<CustomerEntity> customers;

        if (filter == null || filter.isEmpty()) {
            customers = customerService.getAll();
        } else {
            try {
                Integer customerId = Integer.parseInt(filter);
                customers = customerService.getByCustomerId(customerId).map(List::of).orElse(List.of());
            } catch (NumberFormatException e) {
                customers = List.of();
            }
        }

        grid.setItems(customers);
    }
}
