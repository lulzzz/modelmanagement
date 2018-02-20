package com.infosupport.machinelearning.modelmanagement.api;

import com.infosupport.machinelearning.modelmanagement.models.GenericError;
import com.infosupport.machinelearning.modelmanagement.storage.ModelNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ModelNotFoundErrorHandler {
    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<GenericError> handleError(ModelNotFoundException exception) {
        return ResponseEntity
                .status(404)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new GenericError("The model doesn't exist"));
    }
}
