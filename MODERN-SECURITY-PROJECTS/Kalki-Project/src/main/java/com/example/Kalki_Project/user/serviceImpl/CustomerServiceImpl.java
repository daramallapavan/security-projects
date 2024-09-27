package com.example.Kalki_Project.user.serviceImpl;

import com.example.Kalki_Project.user.config.CustomUserDetailsService;
import com.example.Kalki_Project.user.dto.LoginRequest;
import com.example.Kalki_Project.user.exception.CustomerNotFoundException;
import com.example.Kalki_Project.user.entity.Customer;
import com.example.Kalki_Project.user.repository.CustomerRepository;
import com.example.Kalki_Project.user.service.CustomerService;
import com.example.Kalki_Project.user.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    private final CustomUserDetailsService customUserDetailsService;

    @Override
    public Customer registerUser(Customer customer) {
     Optional<Customer> registeredCustomer= customerRepository.findByEmail( customer.getEmail() );
     if (registeredCustomer.isPresent()){
         throw new CustomerNotFoundException( "Email already exist, please try again different email.... " );
     }else{
         customer.setPassword( passwordEncoder.encode( customer.getPassword() ) );
         customer.setRole( "USER" );
         Customer savedCustomer = customerRepository.save( customer );
         return savedCustomer;
     }


    }

    @Override
    public Customer registerUserWithAdmin(Customer customer) {
        Optional<Customer> registeredCustomer= customerRepository.findByEmail( customer.getEmail() );
        if (registeredCustomer.isPresent()){
            throw new CustomerNotFoundException( "Email already exist, please try again different email.... " );
        }else{
            customer.setPassword( passwordEncoder.encode( customer.getPassword() ) );
            customer.setRole( "ADMIN" );
            Customer savedCustomer = customerRepository.save( customer );
            return savedCustomer;
        }

    }

    @Override
    public String login(LoginRequest loginRequest) {

        try{

            authenticationManager
                    .authenticate( new UsernamePasswordAuthenticationToken( loginRequest.getEmail(),loginRequest.getPassword() ) );
        }catch(Exception e){

            throw new CustomerNotFoundException(e.getMessage() );
        }

     UserDetails userDetails= customUserDetailsService.loadUserByUsername( loginRequest.getEmail() );

     String token=   jwtUtil.generateToken( userDetails );

        return token;
    }

    @Override
    public List<Customer> findAllCustomersUserRole() {
       List<Customer> customerList= customerRepository.findAll();

     List<Customer>   customersWithUserRoles=customerList.stream()
               .filter( customer->customer.getRole().equalsIgnoreCase( "USER" ) )
               .collect( Collectors.toList());
        return customersWithUserRoles;
    }

}
