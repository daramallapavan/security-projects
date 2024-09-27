package com.security.securitywithoutjwt.controller;


import com.security.securitywithoutjwt.config.CustomUserDetailsService;
import com.security.securitywithoutjwt.dto.LoginRequestDto;
import com.security.securitywithoutjwt.dto.LoginResponseDto;
import com.security.securitywithoutjwt.dto.RegistrationResponse;
import com.security.securitywithoutjwt.entity.Customer;
import com.security.securitywithoutjwt.repository.CustomerRepository;
import com.security.securitywithoutjwt.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
@Slf4j
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;



    @GetMapping("/all")
    public String all(){
        return "welcome to all users page is visibled";
    }



    @GetMapping("/user")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<String> open(){
        return ResponseEntity.ok("user page is visibled");
    }


    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> one(){
        return ResponseEntity.ok(" page is visibled");
    }


    @PostMapping("/addUser")
    public ResponseEntity<RegistrationResponse> createUser(@RequestBody Customer customer){
        customer.setCustomerPassword(passwordEncoder.encode(customer.getCustomerPassword()));
        customer.setRoles("USER");
             customerRepository.save(customer);
             return new ResponseEntity<>(new RegistrationResponse("user registation successful"),HttpStatus.CREATED);

    }

    @PostMapping("/addAdmin")
    public ResponseEntity<RegistrationResponse> createAdmin(@RequestBody Customer customer){
        customer.setCustomerPassword(passwordEncoder.encode(customer.getCustomerPassword()));
        customer.setRoles("ADMIN");
        customerRepository.save(customer);
        return new ResponseEntity<>(new RegistrationResponse("user registation successful"),HttpStatus.CREATED);

    }



    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginUser(@RequestBody LoginRequestDto loginRequestDto) {


        log.info( "login starts " );

        try {

            authenticationManager.authenticate
                    ( new UsernamePasswordAuthenticationToken( loginRequestDto.getEmail(), loginRequestDto.getPassword() ) );
        }catch (AuthenticationException authenticationException){
             authenticationException.getMessage();
        }

        log.info( "authentication success " );
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginRequestDto.getEmail());


       String token= jwtUtil.generateToken(userDetails);

       log.info( "token generated" );

        return new ResponseEntity<>(new LoginResponseDto(token),HttpStatus.OK);
    }

}
