package com.github.aayushjoshi2709.gateway.dto.Endpoints;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class CreateEndpointDto {
  @NotBlank(message = "Service id is required")
  @Positive(message = "Service id should be a positive number")
  UUID serviceId;

  @NotBlank(message = "Endpoint in the service")
  String endpoint;

  @NotBlank(message = "Roles required to access the endpoint")
  List<String> roles;
}
