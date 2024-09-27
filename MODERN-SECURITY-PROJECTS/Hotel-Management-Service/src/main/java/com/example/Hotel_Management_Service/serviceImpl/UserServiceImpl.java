package com.example.Hotel_Management_Service.serviceImpl;

import com.example.Hotel_Management_Service.config.CustomUserDetailService;
import com.example.Hotel_Management_Service.dto.LoginUserDto;
import com.example.Hotel_Management_Service.dto.UserDto;
import com.example.Hotel_Management_Service.entity.User;
import com.example.Hotel_Management_Service.exception.UserNotFoundException;
import com.example.Hotel_Management_Service.repository.UserRepository;
import com.example.Hotel_Management_Service.service.UserService;
import com.example.Hotel_Management_Service.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    private final CustomUserDetailService customUserDetailService;
    private final JwtUtil jwtUtil;

    @Override
    public User createUser(UserDto userDto) {
        Optional<User> optionalUser=  userRepository.findByEmail( userDto.getEmail() );

     if (optionalUser.isEmpty()){

       User user=  User.builder()
                 .role( "USER" )
                 .name( userDto.getName() )
                 .phoneNumber( userDto.getPhoneNumber() )
                 .password( passwordEncoder.encode( userDto.getPassword() ) )
                 .email( userDto.getEmail() )
                 .build();

         User savedUser = userRepository.save( user );
         return savedUser;

     }
        throw new UserNotFoundException( "user already exist , please try with different email" );

    }

    @Override
    public User createAdmin(UserDto userDto) {
        Optional<User> optionalUser=  userRepository.findByEmail( userDto.getEmail() );

        if (optionalUser.isEmpty()){

            User user=  User.builder()
                    .role( "ADMIN" )
                    .name( userDto.getName() )
                    .phoneNumber( userDto.getPhoneNumber() )
                    .password( passwordEncoder.encode( userDto.getPassword() ) )
                    .email( userDto.getEmail() )
                    .build();

            User savedUser = userRepository.save( user );
            return savedUser;

        }
        throw new UserNotFoundException( "user already exist , please try with different email" );

    }

    @Override
    public List<User> userList() {
        return userRepository.findAll();
    }

    @Override
    public String loginUser(LoginUserDto user) {
        try{
            authenticationManager.authenticate( new UsernamePasswordAuthenticationToken( user.getEmail(),user.getPassword(), Collections.emptyList() ));
        }catch (Exception exception){
          throw   new UserNotFoundException( " : password incorrect");
        }

        UserDetails userDetails = customUserDetailService.loadUserByUsername( user.getEmail() );

       String token= jwtUtil.generateToken( userDetails );

       return token;

    }

    @Override
    public User updateUser(UserDto userDto, String email) {

      User user= getUserByEmail( email );

       user.setEmail( email );
       user.setName( userDto.getName() );
       user.setRole( userDto.getRole() );
       user.setPhoneNumber( userDto.getPhoneNumber() );
       user.setPassword(passwordEncoder.encode( userDto.getPassword() ) );

        User updatedUser = userRepository.save( user );
        return updatedUser;
    }



    @Override
    public void removeUser(String email) {

        User user= getUserByEmail( email );

        userRepository.delete( user );
    }


    private  User getUserByEmail(String email){
        User user=userRepository .findByEmail( email ).orElseThrow(()->new UserNotFoundException( " user not exist...." ));

        return user;
    }


}
