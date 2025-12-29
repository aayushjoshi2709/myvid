package com.github.aayushjoshi2709.myvid.server.entity.Common;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.GenerationType;
import lombok.Getter;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
public class Common {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @CreatedDate
    private LocalDateTime createdAt;
}
