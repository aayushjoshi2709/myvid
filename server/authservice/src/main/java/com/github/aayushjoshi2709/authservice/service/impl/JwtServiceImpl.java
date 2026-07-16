package com.github.aayushjoshi2709.authservice.service.impl;

import com.github.aayushjoshi2709.authservice.entity.User;
import com.github.aayushjoshi2709.authservice.service.JwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class JwtServiceImpl implements JwtService {
    @Value("${appdata.defaults.accessTokenExpiryDays}")
    private Integer accessTokenExpiryDays;

    @Value("${appdata.defaults.jwtSecret}")
    private String jwtSecret;

    public String generateNewAccessToken(User user){
        Instant now = Instant.now();
        SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .subject(user.getId().toString())
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(accessTokenExpiryDays, ChronoUnit.DAYS)))
                .claim("roles", user.getRoles())
                .signWith(key)
                .compact();
    }
}
