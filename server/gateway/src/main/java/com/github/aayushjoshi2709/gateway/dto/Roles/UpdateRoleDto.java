package com.github.aayushjoshi2709.gateway.dto.Roles;

import jakarta.validation.constraints.NotBlank;

public record UpdateRoleDto(
    @NotBlank String name,
    @NotBlank String description) {
}
