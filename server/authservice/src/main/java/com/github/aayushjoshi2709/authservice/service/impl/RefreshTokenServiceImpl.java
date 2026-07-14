package com.github.aayushjoshi2709.authservice.service.impl;

import com.github.aayushjoshi2709.authservice.repository.RefreshTokenRepository;
import com.github.aayushjoshi2709.authservice.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
}
