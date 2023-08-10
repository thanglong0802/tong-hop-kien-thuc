package com.dagoras.edu.api.controller;

import com.dagoras.edu.api.domain.user.UserCreateAdminRequest;
import com.dagoras.edu.api.domain.user.UserCreateRequest;
import com.dagoras.edu.api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
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
}
