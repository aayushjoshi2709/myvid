package com.github.aayushjoshi2709.authservice.dto.user;

public record UpdateUserDto(
    String name,
    String username,
    String email,
    String password
) {
}
