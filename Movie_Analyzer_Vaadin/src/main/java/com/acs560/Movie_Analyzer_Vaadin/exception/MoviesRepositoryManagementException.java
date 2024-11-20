package com.acs560.Movie_Analyzer_Vaadin.exception;

public class MoviesRepositoryManagementException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public MoviesRepositoryManagementException(String message) {
        super(message);
    }

    public MoviesRepositoryManagementException(String message, Throwable cause) {
        super(message, cause);
    }
}
