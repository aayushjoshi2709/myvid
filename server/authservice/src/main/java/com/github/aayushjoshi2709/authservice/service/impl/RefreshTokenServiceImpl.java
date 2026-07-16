package com.github.aayushjoshi2709.authservice.service.impl;

import com.github.aayushjoshi2709.authservice.dto.user.LoginResponseDto;
import com.github.aayushjoshi2709.authservice.entity.RefreshToken;
import com.github.aayushjoshi2709.authservice.entity.User;
import com.github.aayushjoshi2709.authservice.repository.RefreshTokenRepository;
import com.github.aayushjoshi2709.authservice.service.RefreshTokenService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.keygen.Base64StringKeyGenerator;
import org.springframework.security.crypto.keygen.StringKeyGenerator;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${appdata.defaults.refreshTokenExpiryDays}")
    private Integer refreshTokenExpiryDays;

    @Override
    @Scheduled(cron = "0 0 */6 * * *")
    public void deleteExpired() {
        LocalDateTime tokenExpiryTime = LocalDateTime.now().minusDays(refreshTokenExpiryDays);
        this.refreshTokenRepository.deleteExpiredTokens(tokenExpiryTime);
    }

    public RefreshToken findByRefreshToken(String token) {
        return this.refreshTokenRepository.findByRefreshToken(token).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Refresh token not found")
        );
    }

    @Override
    public RefreshToken generateNewRefreshToken(User user, String accessToken){
        StringKeyGenerator generator = new Base64StringKeyGenerator(Base64.getUrlEncoder().withoutPadding(), 22);
        return this.refreshTokenRepository.save(
                new RefreshToken(user.getId(), generator.generateKey(),accessToken)
        );
    }

    @Override
    public void revokeRefreshToken(String token) {
        RefreshToken refreshToken = this.findByRefreshToken(token);
        // Todo: publish revoked refresh tokens to gateway
        this.refreshTokenRepository.delete(refreshToken);
    }

    @Override
    public void revokeRefreshTokenByUser(User user) {
        List<RefreshToken> refreshTokens = this.refreshTokenRepository.findByUserId(user.getId());
        // Todo: publish revoked refresh tokens to gateway
        this.refreshTokenRepository.deleteAll(refreshTokens);
    }

    @Override
    public RefreshToken updateAccessToken(RefreshToken refreshToken, String accessToken) {
        refreshToken.setCurrentAccessToken(accessToken);
        return this.refreshTokenRepository.save(refreshToken);
    }
}
