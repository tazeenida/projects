package com.acs560.FoodManagementSystem.securities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import com.vaadin.flow.spring.security.AuthenticationContext;

/**
 * Service for handling security-related operations in the application.
 * This class provides methods to retrieve the authenticated user and perform logout operations.
 */
@Component
public class SecurityService {

    private final AuthenticationContext authenticationContext;

    /**
     * Constructs a new instance of {@link SecurityService}.
     *
     * @param authenticationContext the context used for managing authentication in the application
     */
    @Autowired
    public SecurityService(AuthenticationContext authenticationContext) {
        this.authenticationContext = authenticationContext;
    }

    /**
     * Retrieves the currently authenticated user.
     *
     * @return the authenticated {@link UserDetails} object of the current user
     * @throws NoSuchElementException if there is no authenticated user
     */
    public UserDetails getAuthenticatedUser() {
        return authenticationContext.getAuthenticatedUser(UserDetails.class).get();
    }

    /**
     * Logs out the currently authenticated user.
     */
    public void logout() {
        authenticationContext.logout(); 
    }
}
