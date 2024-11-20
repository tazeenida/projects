package com.acs560.Movie_Analyzer_Vaadin.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

/**
 * Represents the login view of the Movies Analyzer application.
 * <p>
 * This view allows users to log in to the application. It contains a login form
 * and displays helpful information about valid username/password combinations.
 * </p>
 * 
 * <p>
 * The login view is accessible to anonymous users.
 * </p>
 * 
 * @route login
 * @pageTitle Login | Movies Analyzer
 * @anonymousAllowed
 */
@Route("login") 
@PageTitle("Login | Movies Analyzer")
@AnonymousAllowed
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private static final long serialVersionUID = 574154935938093394L;
    private final LoginForm login = new LoginForm(); 

    /**
     * Constructs a new {@code LoginView} instance.
     * <p>
     * Initializes the layout, sets the login form action, and adds UI components
     * including the title and login form to the view.
     * </p>
     */
    public LoginView(){
        addClassName("login-view");
        setSizeFull(); 
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        login.setAction("login");

        add(new H1("Movies Analyzer"));
        
        add(new Span("Username: user, Password: userpass"));
        add(new Span("Username: admin, Password: adminpass"));
        add(login);
    }

    /**
     * Called before the view is entered.
     * <p>
     * This method checks if there was an authentication error during login and
     * updates the login form to indicate the error.
     * </p>
     * 
     * @param beforeEnterEvent the event that contains information about the 
     *                         navigation
     */
    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if(beforeEnterEvent.getLocation()  
        .getQueryParameters()
        .getParameters()
        .containsKey("error")) {
            login.setError(true);
        }
    }
}
