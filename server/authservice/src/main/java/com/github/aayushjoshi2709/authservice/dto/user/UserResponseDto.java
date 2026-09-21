package com.github.aayushjoshi2709.authservice.dto.user;

import java.util.UUID;

public record UserResponseDto(UUID id, String firstName, String lastName,String username, String email, String phoneNo) {
}
