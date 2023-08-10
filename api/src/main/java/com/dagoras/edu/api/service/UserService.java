package com.dagoras.edu.api.service;

import com.dagoras.edu.api.domain.user.UserCreateAdminRequest;
import com.dagoras.edu.api.domain.user.UserCreateRequest;

public interface UserService {
    String register(UserCreateRequest request);
    String registerAdmin(UserCreateAdminRequest request);
}
