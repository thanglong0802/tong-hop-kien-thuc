package com.dagoras.edu.api.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TokenProvider {
    @Value("${spring.jwt.secret}")
    private String secret;
    @Value("${spring.jwt.expiry}")
    private Long expiry;

//    public String createToken() {
//
//    }
}
