package com.infosupport.machinelearning.modelmanagement.api;

/**
 * Defines a generic error message
 */
public class GenericError {
    private final String message;

    /**
     * Initializes a new instance of {@link GenericError}
     * @param message Message to display
     */
    public GenericError(String message) {
        this.message = message;
    }

    /**
     * Gets the message to display
     * @return
     */
    public String getMessage() {
        return message;
    }
}
