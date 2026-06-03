package com.github.aayushjoshi2709.gateway.dto.Endpoints;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class CreateEndpointDto {
  @NotBlank(message = "Service name is requried")
  String serviceName;

  @NotBlank(message = "Source version is required")
  String sourceVersion;

  @NotBlank(message = "Source endpoint is required")
  String sourceEndpoint;

  @NotBlank(message = "Target version is required")
  String targetVersion;

  @NotBlank(message = "Target endpoint is requried")
  String targetEndpoint;
}
