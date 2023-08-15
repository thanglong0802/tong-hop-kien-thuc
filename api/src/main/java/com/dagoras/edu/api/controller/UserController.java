package com.dagoras.edu.api.controller;

import com.dagoras.edu.api.domain.user.UserCreateAdminRequest;
import com.dagoras.edu.api.domain.user.UserCreateRequest;
import com.dagoras.edu.api.domain.user.UserLoginRequest;
import com.dagoras.edu.api.domain.user.UserResponse;
import com.dagoras.edu.api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> register(@RequestBody @Valid UserCreateRequest request) {
        return ResponseEntity.ok().body(userService.register(request));
    }

    @PostMapping("/admin")
    public ResponseEntity<String> registerAdmin(@RequestBody @Valid UserCreateAdminRequest request) {
        return ResponseEntity.ok().body(userService.registerAdmin(request));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody @Valid UserLoginRequest request) {
        return userService.login(request);
    }
}
