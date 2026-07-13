package com.github.aayushjoshi2709.authservice.dto.user;

public record CreateUserDto(
    String name,
    String username,
    String email,
    String password
) {
}
