package com.infosupport.machinelearning.modelmanagement.api;

import com.infosupport.machinelearning.modelmanagement.storage.ModelNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GenericErrorHandler {
    @ExceptionHandler(IOException.class)
    public ResponseEntity<GenericError> handleIOException(IOException exception) {
        return ResponseEntity
                .status(500)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new GenericError("The server was unable to handle your request."));
    }
}
