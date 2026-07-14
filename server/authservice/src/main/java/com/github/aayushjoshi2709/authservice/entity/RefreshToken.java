package com.github.aayushjoshi2709.authservice.entity;


import com.github.aayushjoshi2709.authservice.entity.common.Common;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name="refresh_tokens")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Setter
public class RefreshToken extends Common {
    @Column
    private UUID userId;
    @Column(name="token", length = 60)
    private String token;
}
