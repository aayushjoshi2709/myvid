package com.github.aayushjoshi2709.authservice.dto.user;

public record UpdateUserDto(
    String firstName,
    String lastName,
    String username,
    String email,
    String password,
    Long phoneNo
) {
}
