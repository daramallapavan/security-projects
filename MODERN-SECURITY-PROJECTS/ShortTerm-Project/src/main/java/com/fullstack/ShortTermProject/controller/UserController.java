package com.fullstack.ShortTermProject.controller;

import com.fullstack.ShortTermProject.dto.UserDto;
import com.fullstack.ShortTermProject.entity.User;
import com.fullstack.ShortTermProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> addUser(@RequestBody UserDto userDto){
        User user = userService.createUser(userDto);
       return ResponseEntity.ok(user);

    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('USER')")
    public String getData(){
        return "this is the api for users";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getDataAdmin(){
        return "this is the api for admin";
    }

}
