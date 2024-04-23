package com.project.microservices.sampleapplication.exception;

import com.project.microservices.sampleapplication.controller.BookController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LogManager.getLogger(BookController.class);
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Object> handleException(BookNotFoundException ex) {
        Map<String, Object> errorBody = new LinkedHashMap<>();
        errorBody.put("error", ex.getMessage());
        LOGGER.error("Encountered following error: {}", ex.getMessage());
        return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidBookException.class)
    public ResponseEntity<Object> handleInvalidBookException(InvalidBookException ex) {
        Map<String, Object> errorBody = new LinkedHashMap<>();
        errorBody.put("error", ex.getMessage());
        LOGGER.error("Encountered following error: {}", ex.getMessage() );
        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }

}
