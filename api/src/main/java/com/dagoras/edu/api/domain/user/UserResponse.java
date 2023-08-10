package com.dagoras.edu.api.domain.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserResponse {
    private String token;

    private String username;

    private String role;

    public UserResponse(String token, String username, String role) {
        this.token = token;
        this.username = username;
        this.role = role;
    }

    public UserResponse(){
    }
}
