package com.github.aayushjoshi2709.authservice.service;

import com.github.aayushjoshi2709.authservice.entity.User;

public interface JwtService {
    String generateNewAccessToken(User user);
}
