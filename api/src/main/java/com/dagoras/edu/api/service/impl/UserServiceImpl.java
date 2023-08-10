package com.dagoras.edu.api.service.impl;

import com.dagoras.edu.api.domain.user.UserCreateAdminRequest;
import com.dagoras.edu.api.domain.user.UserCreateRequest;
import com.dagoras.edu.api.entity.User;
import com.dagoras.edu.api.exception.BusinessException;
import com.dagoras.edu.api.repository.UserRepository;
import com.dagoras.edu.api.service.UserService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String register(UserCreateRequest request) {
        User user;
        user = userRepository.findByUserName(request.getUserName());
        if (!ObjectUtils.allNull(user)) {
            throw new BusinessException(HttpStatus.CONFLICT, "Account already exists");
        }
        user = userRepository.findByEmail(request.getEmail());
        if (!ObjectUtils.allNull(user)) {
            throw new BusinessException(HttpStatus.CONFLICT, "Email already exists");
        }
        User u = new User();
        u.setUserName(request.getUserName());
        u.setPassword(passwordEncoder.encode(request.getPassword()));
        u.setEmail(request.getEmail());
        u.setCreateDate(LocalDateTime.now());
        u.setIsDelete(false);
        u.setRole("ROLE_USER");
        userRepository.save(u);
        return "Đăng ký tài khoản thành công";
    }

    @Override
    public String registerAdmin(UserCreateAdminRequest request) {
        User u1 = userRepository.findByUserName(request.getUserName());
        if (!ObjectUtils.allNull(u1)) {
            throw new BusinessException(HttpStatus.CONFLICT, "Account already exists");
        }
        User u2 = userRepository.findByEmail(request.getEmail());
        if (u2 != null) {
            throw new BusinessException(HttpStatus.CONFLICT, "Email already exists");
        }
        User u = new User();
        u.setUserName(request.getUserName());
        u.setPassword(passwordEncoder.encode(request.getPassword()));
        u.setEmail(request.getEmail());
        u.setCreateDate(LocalDateTime.now());
        u.setIsDelete(false);
        u.setRole("ROLE_" + request.getRole());
        userRepository.save(u);
        return "Đăng ký tài khoản thành công";
    }
}
