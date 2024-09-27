package com.example.Kalki_Project.user.config;

import com.example.Kalki_Project.user.entity.Customer;
import com.example.Kalki_Project.user.exception.CustomerNotFoundException;
import com.example.Kalki_Project.user.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
   @Autowired
    private  CustomerRepository customerRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

      Optional<Customer> customer =customerRepository.findByEmail( username );
        return customer.map( CustomUserDetails::new ).orElseThrow(()->new CustomerNotFoundException( "Email or Password Incorrect" ) );
    }
}
