package com.example.Project_July.serviceImpl;

import com.example.Project_July.confg.CustomUserDetailsService;
import com.example.Project_July.dto.CustomerLoginRequest;
import com.example.Project_July.entity.Customer;
import com.example.Project_July.entity.CustomerRole;
import com.example.Project_July.exception.CustomerException;
import com.example.Project_July.repository.CustomerRepository;
import com.example.Project_July.service.CustomerService;
import com.example.Project_July.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {


    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomerRepository customerRepository;


    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Customer registerCustomer(Customer customer) {

        Optional<Customer> registeredCustomer = customerRepository.findByEmail( customer.getEmail() );

        if (registeredCustomer.isPresent()){
            throw new CustomerException(" Customer already present with this email :"+customer.getEmail());
        }

        customer.setRole( String.valueOf( CustomerRole.USER ) );

        customer.setPassword( passwordEncoder.encode( customer.getPassword() ) );

       Customer saved= customerRepository.save( customer );
        return saved;
    }


    @Override
    public Customer registerCustomerAdmin(Customer customer) {

        Optional<Customer> registeredCustomer = customerRepository.findByEmail( customer.getEmail() );

        if (registeredCustomer.isPresent()){
            throw new CustomerException(" Customer already present with this email :"+customer.getEmail());
        }

        customer.setRole( String.valueOf( CustomerRole.ADMIN ) );

        customer.setPassword( passwordEncoder.encode( customer.getPassword() ) );

        Customer saved= customerRepository.save( customer );
        return saved;
    }

    @Override
    public String login(CustomerLoginRequest request) {


        try{
            authenticationManager
                    .authenticate( new UsernamePasswordAuthenticationToken( request.getEmail(),request.getPassword() ) );
        }catch(AuthenticationException authenticationException) {
            throw new CustomerException( " Password Incorrect" );
        }

       UserDetails userDetails= customUserDetailsService.loadUserByUsername( request.getEmail() );

        String token=jwtUtil.generateToken( userDetails );

        log.info( "token: {}", token);


        return token;
    }


}
