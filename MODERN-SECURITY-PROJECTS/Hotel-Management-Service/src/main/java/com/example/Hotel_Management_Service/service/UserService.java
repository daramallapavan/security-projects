package com.example.Hotel_Management_Service.service;

import com.example.Hotel_Management_Service.dto.LoginUserDto;
import com.example.Hotel_Management_Service.dto.UserDto;
import com.example.Hotel_Management_Service.entity.User;

import java.util.List;

public interface UserService {

    User createUser(UserDto userDto);

    User createAdmin(UserDto userDto);

     List<User> userList();

    String loginUser(LoginUserDto loginUserDto);

    User updateUser(UserDto userDto, String email);

    void removeUser(String email);
}
