package com.acs560.FoodManagementSystem.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("login")
@PageTitle("Login | Food Management Systems")
@AnonymousAllowed
public class LoginView extends VerticalLayout implements BeforeEnterObserver {
    private static final long serialVersionUID = 574154935938093394L;
    private final LoginForm login = new LoginForm(); 

    public LoginView() {
        addClassName("login-view");
        setSizeFull(); 
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        login.setAction("login");

        add(new H1("Food Management System"));
        
        add(new Span("Username: user, Password: userpass"));
        add(new Span("Username: admin, Password: adminpass"));
        add(login);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        // Inform the user about an authentication error
        if (beforeEnterEvent.getLocation().getQueryParameters().getParameters().containsKey("error")) {
            login.setError(true);
            Notification.show("Invalid username or password", 3000, Notification.Position.MIDDLE);
        }
    }
}
