package com.github.aayushjoshi2709.authservice.dto.user;

public record CreateUserDto(
    String firstName,
    String lastName,
    String username,
    String email,
    String password,
    Long phoneNo,
    String profilePicUrl
) {
}
