package com.acs560.FoodManagementSystem.views.Restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import com.acs560.FoodManagementSystem.entities.RestaurantEntity;
import com.acs560.FoodManagementSystem.models.Restaurant;
import com.acs560.FoodManagementSystem.services.RestaurantService;
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
 * The {@link RestaurantListView} class is responsible for displaying a list of
 * restaurants in the Food Management System. It provides functionalities to
 * filter restaurants and view their details.
 * 
 * This class extends {@link VerticalLayout} and contains a grid for displaying
 * restaurant entities, a form for restaurant details, and a filter field for
 * searching by restaurant name.
 */
@SpringComponent
@Scope("prototype")
@PermitAll
@Route(value = "restaurant", layout = MainLayout.class)
@PageTitle("Restaurants | Food Management System")
public class RestaurantListView extends VerticalLayout {

    private static final long serialVersionUID = 1L;

    @Autowired
    private RestaurantService restaurantService;

    private final Grid<RestaurantEntity> grid;
    private final TextField filterText;
    private final RestaurantFormView restaurantForm;

    /**
     * Constructs a new instance of {@link RestaurantListView}.
     * Initializes the view with a grid to display restaurants, a form to edit
     * restaurant details, and a filter field for searching restaurants.
     *
     * @param restaurantService the service to handle restaurant-related operations
     */
    public RestaurantListView(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;

        addClassName("list-view");
        setSizeFull();

        grid = createGrid();
        restaurantForm = createForm();
        filterText = createFilter();

        add(createToolbar(filterText), getContent());
        updateGrid(); 
        closeForm();
    }

    /**
     * Creates and returns a form view for restaurant details.
     * 
     * @return the created restaurant form view
     */
    private RestaurantFormView createForm() {
        RestaurantFormView restaurantForm = new RestaurantFormView();
        restaurantForm.setVisible(false); 
        return restaurantForm;
    }

    /**
     * Creates and returns a grid for displaying restaurant entities.
     * 
     * @return the created grid for restaurant entities
     */
    private Grid<RestaurantEntity> createGrid() {
        Grid<RestaurantEntity> grid = new Grid<>(RestaurantEntity.class);
        grid.addClassNames("restaurant-grid");
        grid.setSizeFull();
        grid.setColumns("restaurantId", "restaurantName", "foodPreparationTime", "deliveryTime");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        return grid;
    }

    /**
     * Creates and returns a text field for filtering restaurants by name.
     * 
     * @return the created filter text field
     */
    private TextField createFilter() {
        TextField filterText = new TextField();
        filterText.setValueChangeTimeout(1000);
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateGrid()); 

        return filterText;
    }

    /**
     * Creates and returns a toolbar containing the filter text field.
     * 
     * @param filterText the text field for filtering restaurants
     * @return the created toolbar component
     */
    private Component createToolbar(TextField filterText) {
        var toolbar = new HorizontalLayout(filterText);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    /**
     * Creates and returns a layout containing the grid and the restaurant form.
     * 
     * @return the content layout consisting of the grid and restaurant form
     */
    private HorizontalLayout getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, restaurantForm);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, restaurantForm);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    /**
     * Updates the grid with the list of restaurants based on the current filter.
     * If the filter is empty, all restaurants are displayed. Otherwise, the grid
     * is populated with restaurants matching the filter.
     */
    private void updateGrid() {
        String filter = filterText.getValue();
        List<RestaurantEntity> restaurants;

        if (filter == null || filter.isEmpty()) {
            restaurants = restaurantService.getAll();
        } else {
            restaurants = restaurantService.getByRestaurantName(filter);
        }

        grid.setItems(restaurants);
    }

    /**
     * Closes the restaurant form by hiding it and removing the editing class.
     */
    private void closeForm() {
        restaurantForm.setVisible(false);
        removeClassName("editing");
    }
}
