package com.github.aayushjoshi2709.authservice.repository;

import com.github.aayushjoshi2709.authservice.entity.RefreshToken;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {
    @Modifying
    @Transactional
    @Query("DELETE FROM RefreshToken rt WHERE rt.createdAt <= :tokenExpiryTime")
    void deleteExpiredTokens(@Param("tokenExpiryTime") LocalDateTime tokenExpiryTime);

    Optional<RefreshToken> findByRefreshToken(String refreshToken);

    List<RefreshToken> findByUserId(UUID userId);
}
