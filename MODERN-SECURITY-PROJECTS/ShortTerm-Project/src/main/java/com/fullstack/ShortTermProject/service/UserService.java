package com.fullstack.ShortTermProject.service;

import com.fullstack.ShortTermProject.dto.UserDto;
import com.fullstack.ShortTermProject.entity.User;

public interface UserService {

    public User createUser(UserDto userDto);
}
