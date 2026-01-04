package com.github.aayushjoshi2709.myvid.server.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    public String generateToken(String username);
    String extractUserName(String token);
    boolean validateToken(String token, UserDetails userDetails);
}
