package com.example.Kalki_Project.user.controller;

import com.example.Kalki_Project.user.dto.LoginRequest;
import com.example.Kalki_Project.user.entity.Customer;
import com.example.Kalki_Project.user.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/registerUser")
    public ResponseEntity<?> createCustomerWithUserRole(@RequestBody Customer customer){
         Customer savedCustomer=customerService.registerUser(customer);
         return new ResponseEntity<>( savedCustomer, HttpStatus.CREATED );
    }

    @PostMapping("/registerAdmin")
    public ResponseEntity<?> createCustomerWithAdminRole(@RequestBody Customer customer){
        Customer savedCustomer=customerService.registerUserWithAdmin(customer);
        return new ResponseEntity<>( savedCustomer, HttpStatus.CREATED );
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
      String response=  customerService.login(loginRequest);

      return new ResponseEntity<>( response,HttpStatus.CREATED );
    }

    @GetMapping("/listOfCustomersWithUserRole")
    @PreAuthorize( "hasAuthority('ADMIN')" )
    public ResponseEntity<List<Customer>> customersListWithUserRole(){
        List<Customer> customerList=customerService.findAllCustomersUserRole();

        return new ResponseEntity<>( customerList,HttpStatus.OK );
    }


}
