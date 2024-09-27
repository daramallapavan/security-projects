package com.example.Kalki_Project.user.service;

import com.example.Kalki_Project.user.dto.LoginRequest;
import com.example.Kalki_Project.user.entity.Customer;

import java.util.List;


public interface CustomerService {

    Customer registerUser(Customer customer);

    Customer registerUserWithAdmin(Customer customer);

    String login(LoginRequest loginRequest);

    List<Customer> findAllCustomersUserRole();
}
