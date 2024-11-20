package com.acs560.FoodManagementSystem.views;

import org.springframework.beans.factory.annotation.Autowired;

import com.acs560.FoodManagementSystem.models.Order;
import com.acs560.FoodManagementSystem.securities.SecurityService;
import com.acs560.FoodManagementSystem.views.Customer.CustomerListView;
import com.acs560.FoodManagementSystem.views.Order.OrderHistoryView;
import com.acs560.FoodManagementSystem.views.Order.OrderListView;
import com.acs560.FoodManagementSystem.views.Restaurant.RestaurantListView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;

/**
 * The {@link MainLayout} class represents the main layout for the Food Management System application.
 * It extends {@link AppLayout} and provides a header and a navigation drawer for navigating between different views.
 */
public class MainLayout extends AppLayout {
    private static final long serialVersionUID = -5291741451913578403L;

    @Autowired
    private final SecurityService securityService;

    /**
     * Constructs a new instance of {@link MainLayout}.
     * Initializes the layout and sets up the header and navigation drawer.
     *
     * @param securityService the {@link SecurityService} used for user authentication and logout
     */
    public MainLayout(SecurityService securityService) {
        this.securityService = securityService;
        createHeader();
        createDrawer();
    }

    /**
     * Creates the header of the layout, which includes the application logo and a logout button.
     * The logout button displays the authenticated user's username.
     */
    private void createHeader() {
        H1 logo = new H1("Food Management System");
        logo.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.MEDIUM);
        
        String username = securityService.getAuthenticatedUser().getUsername();
        Button logout = new Button("Log out " + username, e -> securityService.logout());
        
        var header = new HorizontalLayout(new DrawerToggle(), logo, logout);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidthFull();
        header.addClassNames(LumoUtility.Padding.Vertical.NONE, LumoUtility.Padding.Horizontal.MEDIUM);
        
        addToNavbar(header);
    }

    /**
     * Creates the navigation drawer with links to different views in the application.
     * Includes links to the order, customer, Order History, and restaurant views.
     */
    private void createDrawer() {
        RouterLink orderLink = new RouterLink("Orders", OrderListView.class);
        orderLink.setHighlightCondition(HighlightConditions.sameLocation());
        RouterLink orderHistoryLink = new RouterLink("Order History", OrderHistoryView.class);
        
        RouterLink customerLink = new RouterLink("Customers", CustomerListView.class);
        RouterLink restaurantLink = new RouterLink("Restaurants", RestaurantListView.class);
        
        addToDrawer(new VerticalLayout(orderLink, customerLink, restaurantLink, orderHistoryLink));
    }
}
