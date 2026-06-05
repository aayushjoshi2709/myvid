package com.github.aayushjoshi2709.gateway.dto.Service;

import jakarta.validation.constraints.NotBlank;

public record CreateServiceDto(
    @NotBlank String ServiceName,
    @NotBlank String ServiceUrl) {
}
