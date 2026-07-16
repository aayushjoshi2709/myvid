package com.github.aayushjoshi2709.authservice.service;

import com.github.aayushjoshi2709.authservice.entity.RefreshToken;

import java.util.UUID;

public interface RefreshTokenService {
    void deleteExpired();
    RefreshToken generateNewRefreshToken(UUID userId, String accessToken);
    void revokeRefreshToken(String refreshToken);
    void revokeRefreshTokenByUser(UUID userId);
    RefreshToken findByRefreshToken(String token);
    RefreshToken updateAccessToken(RefreshToken refreshToken, String accessToken);
}
