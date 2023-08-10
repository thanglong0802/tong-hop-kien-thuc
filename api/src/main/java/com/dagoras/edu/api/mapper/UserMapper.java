package com.dagoras.edu.api.mapper;

import com.dagoras.edu.api.entity.User;
import com.dagoras.edu.api.jwt.CustomUserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User convertValue(CustomUserDetails userDetails) {
        User user = new User();
        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        user.setEmail(userDetails.getEmail());
        user.setRole(userDetails.getRole());
        return user;
    }
}
