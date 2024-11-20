package com.acs560.Movie_Analyzer_Vaadin.views;

import org.springframework.beans.factory.annotation.Autowired;

import com.acs560.Movie_Analyzer_Vaadin.security.SecurityService;
import com.acs560.Movie_Analyzer_Vaadin.views.movies.MovieView;
import com.acs560.Movie_Analyzer_Vaadin.views.types.TypeView;
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
 * The main layout for the application
 */
public class MainLayout extends AppLayout {

	private static final long serialVersionUID = -5291741451913578403L;
	
	@Autowired
    private final SecurityService securityService;

	/**
	 * Constructor
	 */
	public MainLayout(SecurityService securityService) {
		this.securityService = securityService;
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("Movie Analyzer");
        logo.addClassNames(
            LumoUtility.FontSize.LARGE,
            LumoUtility.Margin.MEDIUM);
        
        String u = securityService.getAuthenticatedUser().getUsername();
        Button logout = new Button("Log out " + u, e -> securityService.logout());

        var header = new HorizontalLayout(new DrawerToggle(), logo, logout);

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidthFull();
        header.addClassNames(
            LumoUtility.Padding.Vertical.NONE,
            LumoUtility.Padding.Horizontal.MEDIUM);

        addToNavbar(header); 
    }

    /**
     * Create the drawer
     */
    private void createDrawer() {
    	RouterLink MoviesLink = new RouterLink("Movies", MovieView.class);
    	MoviesLink.setHighlightCondition(HighlightConditions.sameLocation());
    	
        RouterLink TypeLink = new RouterLink("Type", TypeView.class);
        
        addToDrawer(new VerticalLayout(MoviesLink, TypeLink));
    }
}