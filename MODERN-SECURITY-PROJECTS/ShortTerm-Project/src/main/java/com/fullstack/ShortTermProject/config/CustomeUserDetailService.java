package com.fullstack.ShortTermProject.config;

import com.fullstack.ShortTermProject.entity.User;
import com.fullstack.ShortTermProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class CustomeUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<User> user= userRepository.findByEmail(username);
        return user.map(CustomUserDetail::new)
                .orElseThrow(()->new UsernameNotFoundException("not found"));
    }
}
