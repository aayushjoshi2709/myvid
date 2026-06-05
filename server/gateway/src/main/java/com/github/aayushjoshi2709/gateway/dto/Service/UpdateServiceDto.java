package com.github.aayushjoshi2709.gateway.dto.Service;

import jakarta.validation.constraints.NotEmpty;

public record UpdateServiceDto(
    @NotEmpty String ServiceName,
    @NotEmpty String ServiceUrl) {
}
