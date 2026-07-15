package com.github.aayushjoshi2709.authservice.service.impl;

import com.github.aayushjoshi2709.authservice.dto.user.LoginResponseDto;
import com.github.aayushjoshi2709.authservice.entity.RefreshToken;
import com.github.aayushjoshi2709.authservice.entity.User;
import com.github.aayushjoshi2709.authservice.repository.RefreshTokenRepository;
import com.github.aayushjoshi2709.authservice.service.RefreshTokenService;
import com.github.aayushjoshi2709.authservice.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.keygen.Base64StringKeyGenerator;
import org.springframework.security.crypto.keygen.StringKeyGenerator;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserService userService;

    @Value("${appdata.defaults.refreshTokenExpiryDays}")
    private Integer refreshTokenExpiryDays;

    @Value("${appdata.defaults.accessTokenExpiryDays}")
    private Integer accessTokenExpiryDays;

    @Value("${appdata.defaults.jwtSecret}")
    private String jwtSecret;

    @Override
    @Scheduled(cron = "0 0 */6 * * *")
    public void deleteExpired() {
        LocalDateTime tokenExpiryTime = LocalDateTime.now().minusDays(refreshTokenExpiryDays);
        this.refreshTokenRepository.deleteExpiredTokens(tokenExpiryTime);
    }

    private String generateJWTToken(User user){
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

    @Override
    public LoginResponseDto getNewRefreshToken(User user){
        String currentAccessToken = this.generateJWTToken(user);
        StringKeyGenerator generator = new Base64StringKeyGenerator(Base64.getUrlEncoder().withoutPadding(), 22);
        RefreshToken savedRefreshToken = this.refreshTokenRepository.save(
                new RefreshToken(user.getId(), generator.generateKey(),currentAccessToken)
        );
        return  new LoginResponseDto(
                currentAccessToken,
                savedRefreshToken.getRefreshToken()
        );
    }

    @Override
    public void revokeRefreshToken(String token) {
        RefreshToken refreshToken = this.refreshTokenRepository.findByRefreshToken(token);
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
    public LoginResponseDto getNewAccessToken(String token) {
        RefreshToken refreshToken = this.refreshTokenRepository.findByRefreshToken(token);
        User user = this.userService.findUserById(refreshToken.getUserId());
        String currentAccessToken = this.generateJWTToken(user);
        refreshToken.setCurrentAccessToken(currentAccessToken);
        return  new LoginResponseDto(
                currentAccessToken,
                token
        );
    }
}
