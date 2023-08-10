package com.dagoras.edu.api.jwt;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenProvider {

    private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);
    @Value("${jwt.token.secret}")
    private String secret;
    @Value("${jwt.token.expiration}")
    private Long expiry;

    public String createToken(CustomUserDetails customUserDetails) {
        Date now = new Date();
        Date dateExpired = new Date(now.getTime() + expiry);
        return Jwts.builder()
                .setSubject(customUserDetails.getUsername()) // set định danh cho mỗi user theo username
                .setIssuedAt(now) // ngày bắt đầu có hiệu lực
                .setExpiration(dateExpired) // ngày hết
                .signWith(SignatureAlgorithm.HS512, secret) // chữ ký: có 2 tham số: 1 là giải thuật, 2 là key
                .compact(); // build ra thành chuỗi jwt
    }

    public String getSubjectFromJwt(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public String getSubject(String token) {
        return parseClaims(token).getSubject();
    }

    private Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException e) {
            logger.error("JWT claim String is empty");
        }
        return false;
    }

}
