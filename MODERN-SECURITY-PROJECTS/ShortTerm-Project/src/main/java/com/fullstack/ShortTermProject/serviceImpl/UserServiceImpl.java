package com.fullstack.ShortTermProject.serviceImpl;

import com.fullstack.ShortTermProject.dto.UserDto;
import com.fullstack.ShortTermProject.entity.User;
import com.fullstack.ShortTermProject.repository.UserRepository;
import com.fullstack.ShortTermProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public User createUser(UserDto userDto) {
        User user=User.builder()
                .userName(userDto.getUserName())
                .email(userDto.getEmail())
                .userPassword(passwordEncoder.encode(userDto.getUserPassword()))
                .roles(userDto.getRoles())
                .build();

        User savedUser = userRepository.save(user);

        return savedUser;
    }
}
