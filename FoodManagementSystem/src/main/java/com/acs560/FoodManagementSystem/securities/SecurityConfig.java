package com.acs560.FoodManagementSystem.securities;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.vaadin.flow.spring.security.VaadinWebSecurity;
import com.acs560.FoodManagementSystem.views.LoginView;

/**
 * Security configuration for the Food Management System.
 * <p>
 * This class extends {@link VaadinWebSecurity} to provide custom security settings,
 * including user authentication and authorization for various HTTP requests.
 * </p>
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig extends VaadinWebSecurity {
    
    /**
     * Configures the security settings for HTTP requests.
     * <p>
     * This method defines which requests are permitted without authentication and sets
     * the login view for the application. It allows GET requests for PNG images
     * to be accessed without authentication.
     * </p>
     *
     * @param http the {@link HttpSecurity} object used to configure security settings
     * @throws Exception if an error occurs while configuring security settings
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> 
            auth.requestMatchers(
                AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/images/*.png")).permitAll());
        super.configure(http);
        setLoginView(http, LoginView.class);
    }

    /**
     * Creates an in-memory user details service with predefined users.
     * <p>
     * This method defines two users: a regular user with username "user" and
     * password "userpass", and an admin user with username "admin" and password
     * "adminpass". Both users are granted roles for authorization.
     * </p>
     *
     * @return a {@link UserDetailsService} that manages user authentication
     */
    @Bean
    public UserDetailsService users() {
        UserDetails user = User.builder()
                .username("user")
                .password("{noop}userpass")
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}adminpass")
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}
