package com.github.aayushjoshi2709.authservice.service;

import com.github.aayushjoshi2709.authservice.dto.user.LoginResponseDto;
import com.github.aayushjoshi2709.authservice.entity.RefreshToken;
import com.github.aayushjoshi2709.authservice.entity.User;

public interface RefreshTokenService {
    void deleteExpired();
    RefreshToken generateNewRefreshToken(User user, String accessToken);
    void revokeRefreshToken(String refreshToken);
    void revokeRefreshTokenByUser(User user);
    RefreshToken findByRefreshToken(String token);
    RefreshToken updateAccessToken(RefreshToken refreshToken, String accessToken);
}
