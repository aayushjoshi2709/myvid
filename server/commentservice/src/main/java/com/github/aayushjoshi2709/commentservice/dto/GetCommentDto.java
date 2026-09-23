package com.github.aayushjoshi2709.commentservice.dto;

import com.github.aayushjoshi2709.commentservice.entity.User;

import java.util.UUID;

public record GetCommentDto(UUID id, String comment, User userDetails) {
}
