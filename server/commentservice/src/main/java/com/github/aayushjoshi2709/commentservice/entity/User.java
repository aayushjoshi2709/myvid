package com.github.aayushjoshi2709.commentservice.entity;

import jakarta.persistence.Column;

import java.util.UUID;

public class User {
    @Column(name = "userId", length = 25, nullable = false, unique = true)
    private UUID userId;

    @Column(name = "username", length = 25, nullable = false, unique = true)
    private String username;

    @Column(name = "profilePicUrl", length = 150)
    private String profilePicUrl;
}
