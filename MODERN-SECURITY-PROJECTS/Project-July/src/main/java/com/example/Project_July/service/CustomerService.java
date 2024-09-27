package com.example.Project_July.service;

import com.example.Project_July.dto.CustomerLoginRequest;
import com.example.Project_July.entity.Customer;

public interface CustomerService {

    Customer registerCustomer(Customer customer);

    Customer registerCustomerAdmin(Customer customer);

    String login(CustomerLoginRequest request);


}
