package com.example.Kalki_Project.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<?> handleCustomerNotFoundException(CustomerNotFoundException customerNotFoundException){
        return new ResponseEntity<>( customerNotFoundException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
    }
}
