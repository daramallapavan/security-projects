package com.example.Project_July.controller;

import com.example.Project_July.dto.CustomerLoginRequest;
import com.example.Project_July.entity.Customer;
import com.example.Project_July.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {


    @Autowired
    private CustomerService customerService;


    @PostMapping("/registerUser")
    public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer) {

        Customer createCustomer = customerService.registerCustomer( customer );

        return new ResponseEntity<>( createCustomer, HttpStatus.CREATED );
    }

    @PostMapping("/registerAdmin")
    public ResponseEntity<Customer> registerCustomerAdmin(@RequestBody Customer customer) {

        Customer createCustomer = customerService.registerCustomerAdmin( customer );

        return new ResponseEntity<>( createCustomer, HttpStatus.CREATED );
    }



    @PostMapping("/login")
    public ResponseEntity<String> loginCustomer(@RequestBody CustomerLoginRequest request) {


        String response = customerService.login( request );

        return new ResponseEntity<>( response, HttpStatus.OK );

    }


    @GetMapping("/info")
    @PreAuthorize("hasAuthority('USER')")
    public String checking() {
        return " This is the Application";
    }


}
