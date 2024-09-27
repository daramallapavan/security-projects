package com.example.Hotel_Management_Service.config;

import com.example.Hotel_Management_Service.entity.User;
import com.example.Hotel_Management_Service.exception.UserNotFoundException;
import com.example.Hotel_Management_Service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Optional<User> user= userRepository.findByEmail( username );
        return user.map( CustomUserDetails::new ).orElseThrow(()->new UserNotFoundException( " Username or Password Incorrect" ) );
    }
}
