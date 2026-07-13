package com.github.aayushjoshi2709.commentservice.entity;

import com.github.aayushjoshi2709.commentservice.entity.common.Common;
import jakarta.persistence.Column;

import java.util.UUID;

public class Like extends Common {
    @Column(name = "userId", nullable = false)
    private UUID userId;

    @Column(name = "commentId", nullable = false)
    private UUID commentId;
}
