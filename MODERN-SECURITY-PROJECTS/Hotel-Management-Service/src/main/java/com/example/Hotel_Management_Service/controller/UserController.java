package com.example.Hotel_Management_Service.controller;

import com.example.Hotel_Management_Service.dto.LoginUserDto;
import com.example.Hotel_Management_Service.dto.UserDto;
import com.example.Hotel_Management_Service.entity.User;
import com.example.Hotel_Management_Service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<?>  registerUser(@RequestBody UserDto userDto){
        User user = userService.createUser( userDto );
        return new ResponseEntity<>( user, HttpStatus.CREATED );
    }

    @PutMapping("/updateUser")
    @PreAuthorize( "hasAuthority('ADMIN')" )
    public ResponseEntity<?>  updateUser(@RequestParam String email,
                                         @RequestBody UserDto userDto){
        User user = userService.updateUser( userDto,email );
        return new ResponseEntity<>( user, HttpStatus.CREATED );
    }

    @DeleteMapping("/deleteUser")
    @PreAuthorize( "hasAuthority('ADMIN')" )
    public ResponseEntity<?> deleteUser(@RequestParam String email){
        userService.removeUser(email);
        return new ResponseEntity<>( "user removed "+email ,HttpStatus.OK);
    }

    @PostMapping("/registerAdmin")
    public ResponseEntity<?>  registerAdmin(@RequestBody UserDto userDto){
        User user = userService.createAdmin( userDto );
        return new ResponseEntity<>( user, HttpStatus.CREATED );
    }


    @PostMapping("/login")
    public ResponseEntity<?>  loginUser(@RequestBody LoginUserDto loginUserDto){
        String  token = userService.loginUser(loginUserDto);
        return new ResponseEntity<>( token, HttpStatus.CREATED );
    }



    @GetMapping("/listOfUsers")
    @PreAuthorize( "hasAuthority('ADMIN')" )
    public ResponseEntity<List<User>>  listOfUsers(){
        List<User> users=userService.userList();
        return new ResponseEntity<>( users,HttpStatus.OK );
    }
}
