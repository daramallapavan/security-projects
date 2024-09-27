package com.security.securitywithoutjwt.config;

import com.security.securitywithoutjwt.entity.Customer;
import com.security.securitywithoutjwt.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
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
        Optional<Customer> customer = customerRepository.findByCustomerEmail(username);

        return customer.map(CustomUserDetails::new).orElseThrow(()->new UsernameNotFoundException("not found with id"));
    }
}
