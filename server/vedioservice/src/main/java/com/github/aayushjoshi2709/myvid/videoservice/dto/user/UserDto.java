package com.github.aayushjoshi2709.myvid.videoservice.dto.user;

import java.util.UUID;

public record UserDto(UUID id, String username, String profilePicUrl) {
}
