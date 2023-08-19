package com.dagoras.edu.api.service.impl;

import com.dagoras.edu.api.domain.user.UserCreateAdminRequest;
import com.dagoras.edu.api.domain.user.UserCreateRequest;
import com.dagoras.edu.api.domain.user.UserLoginRequest;
import com.dagoras.edu.api.domain.user.UserResponse;
import com.dagoras.edu.api.entity.Student;
import com.dagoras.edu.api.entity.User;
import com.dagoras.edu.api.exception.BusinessException;
import com.dagoras.edu.api.jwt.CustomUserDetails;
import com.dagoras.edu.api.jwt.TokenProvider;
import com.dagoras.edu.api.repository.StudentRepository;
import com.dagoras.edu.api.repository.UserRepository;
import com.dagoras.edu.api.service.UserService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final AuthenticationManager authManager;
    private final StudentRepository studentRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenProvider tokenProvider, AuthenticationManager authManager, StudentRepository studentRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.authManager = authManager;
        this.studentRepository = studentRepository;
    }

    @Override
    public String register(UserCreateRequest request) {
        User user;
        user = userRepository.findByUsername(request.getUsername());
        if (!ObjectUtils.allNull(user)) {
            throw new BusinessException(HttpStatus.CONFLICT, "Account already exists");
        }
        user = userRepository.findByEmail(request.getEmail());
        if (!ObjectUtils.allNull(user)) {
            throw new BusinessException(HttpStatus.CONFLICT, "Email already exists");
        }

        checkPhoneNumberExists(request.getPhoneNumber());

        User u = new User();
        u.setUsername(request.getUsername());
        u.setPassword(passwordEncoder.encode(request.getPassword()));
        u.setEmail(request.getEmail());
        u.setCreateDate(LocalDateTime.now());
        u.setIsDelete(false);
        u.setRole("USER");
        userRepository.save(u);

        // Tạo tài khoản user kèm 1 student
        Student student = new Student();
        student.setUserName(request.getUsername());
        student.setEmail(request.getEmail());
        student.setPhoneNumber(request.getPhoneNumber());
        student.setCreateDate(LocalDateTime.now());
        student.setIsDelete(false);
        studentRepository.save(student);

        return "Đăng ký tài khoản thành công";
    }

    private boolean checkPhoneNumberExists(String phoneNumber) {
        Student st = studentRepository.findByPhoneNumber(phoneNumber);
        if (!ObjectUtils.allNull(st)) {
            throw new BusinessException(HttpStatus.CONFLICT, "Số điện thoại đã tồn tại");
        }
        return true;
    }

    @Override
    public String registerAdmin(UserCreateAdminRequest request) {
        User u1 = userRepository.findByUsername(request.getUsername());
        if (!ObjectUtils.allNull(u1)) {
            throw new BusinessException(HttpStatus.CONFLICT, "Account already exists");
        }
        User u2 = userRepository.findByEmail(request.getEmail());
        if (u2 != null) {
            throw new BusinessException(HttpStatus.CONFLICT, "Email already exists");
        }
        User u = new User();
        u.setUsername(request.getUsername());
        u.setPassword(passwordEncoder.encode(request.getPassword()));
        u.setEmail(request.getEmail());
        u.setCreateDate(LocalDateTime.now());
        u.setIsDelete(false);
        u.setRole(StringUtils.upperCase(request.getRole()));
        userRepository.save(u);
        return "Đăng ký tài khoản thành công";
    }

    @Override
    public ResponseEntity<UserResponse> login(UserLoginRequest request) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
            String createToken = tokenProvider.createToken(user);
            UserResponse userResponse = new UserResponse(createToken, user.getUsername(), user.getRole());
            return ResponseEntity.ok().body(userResponse);
        } catch (Exception ex) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "User name or password does not exist");
        }
    }
}
