package com.github.aayushjoshi2709.authservice.service.impl;

import com.github.aayushjoshi2709.authservice.dto.user.LoginResponseDto;
import com.github.aayushjoshi2709.authservice.entity.RefreshToken;
import com.github.aayushjoshi2709.authservice.entity.User;
import com.github.aayushjoshi2709.authservice.repository.RefreshTokenRepository;
import com.github.aayushjoshi2709.authservice.service.JwtService;
import com.github.aayushjoshi2709.authservice.service.RefreshTokenService;

import com.github.aayushjoshi2709.authservice.service.UserService;
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
    private final UserService userService;
    private final JwtService jwtService;

    @Value("${appdata.defaults.refreshTokenExpiryDays}")
    private Integer refreshTokenExpiryDays;

    @Override
    @Scheduled(cron = "0 0 */6 * * *")
    public void deleteExpired() {
        LocalDateTime tokenExpiryTime = LocalDateTime.now().minusDays(refreshTokenExpiryDays);
        this.refreshTokenRepository.deleteExpiredTokens(tokenExpiryTime);
    }

    private RefreshToken findByRefreshToken(String token) {
        return this.refreshTokenRepository.findByRefreshToken(token).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Refresh token not found")
        );
    }

    @Override
    public LoginResponseDto getNewRefreshToken(User user){
        String currentAccessToken = this.jwtService.generateJWTToken(user);
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
    public LoginResponseDto getNewAccessToken(String token) {
        RefreshToken refreshToken = this.findByRefreshToken(token);
        User user = this.userService.findUserById(refreshToken.getUserId());
        String currentAccessToken = this.jwtService.generateJWTToken(user);
        refreshToken.setCurrentAccessToken(currentAccessToken);
        return  new LoginResponseDto(
                currentAccessToken,
                token
        );
    }
}
