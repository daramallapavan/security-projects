package com.example.Project_July.confg;

import com.example.Project_July.entity.Customer;
import com.example.Project_July.exception.CustomerException;
import com.example.Project_July.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    private CustomerRepository customerRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> customer = customerRepository.findByEmail( username );
        return customer.map( CustomUserDetails::new ).orElseThrow(()-> new CustomerException( " Email is Incorrect" ));
    }
}
