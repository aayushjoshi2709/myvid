package com.github.aayushjoshi2709.authservice.service;

import com.github.aayushjoshi2709.authservice.dto.user.LoginResponseDto;
import com.github.aayushjoshi2709.authservice.entity.User;

public interface RefreshTokenService {
    void deleteExpired();
    LoginResponseDto getNewRefreshToken(User user);
    void revokeRefreshToken(String refreshToken);
    void revokeRefreshTokenByUser(User user);
    LoginResponseDto getNewAccessToken(String token);
}
