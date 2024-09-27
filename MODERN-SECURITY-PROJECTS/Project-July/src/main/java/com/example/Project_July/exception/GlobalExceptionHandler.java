package com.example.Project_July.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<?> checkingCustomerExistOrNot(CustomerException customerException){
        return new ResponseEntity<>( customerException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
    }
}
