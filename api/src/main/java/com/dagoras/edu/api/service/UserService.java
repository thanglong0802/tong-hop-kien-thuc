package com.dagoras.edu.api.service;

import com.dagoras.edu.api.domain.user.UserCreateAdminRequest;
import com.dagoras.edu.api.domain.user.UserCreateRequest;
import com.dagoras.edu.api.domain.user.UserLoginRequest;
import com.dagoras.edu.api.domain.user.UserResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {
    String register(UserCreateRequest request);
    String registerAdmin(UserCreateAdminRequest request);
    ResponseEntity<UserResponse> login(UserLoginRequest request);
}
