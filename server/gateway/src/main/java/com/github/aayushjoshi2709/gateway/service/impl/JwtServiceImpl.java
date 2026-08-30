package com.github.aayushjoshi2709.gateway.service.impl;

import com.github.aayushjoshi2709.gateway.service.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.UUID;

import javax.crypto.SecretKey;

@Service
public class JwtServiceImpl implements JwtService {
  @Value("${appdata.defaults.jwtSecret}")
  private String jwtSecret;

  private SecretKey secretKey;

  @PostConstruct
  private void init() {
    this.secretKey = Keys.hmacShaKeyFor(
        jwtSecret.getBytes(StandardCharsets.UTF_8));
  }

  private Claims parseJwtToken(String token) {
    return Jwts.parser()
        .verifyWith(secretKey)
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }

  @Override
  public UUID getUserId(String token) {
    String userId = this.parseJwtToken(token).getSubject();
    return UUID.fromString(userId);
  }

  @Override
  public ArrayList<String> getRoles(String token) {
    List<String> roles = this.parseJwtToken(token).get("roles", List.class);
    return new ArrayList<>(roles);
  }
}
