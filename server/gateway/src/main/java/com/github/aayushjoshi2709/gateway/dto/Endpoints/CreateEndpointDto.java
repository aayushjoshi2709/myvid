package com.github.aayushjoshi2709.gateway.dto.Endpoints;

import com.github.aayushjoshi2709.gateway.entity.enums.HttpMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
  @NotNull(message = "Service id is required")
  UUID serviceId;

  @NotBlank(message = "Endpoint in the service")
  String endpoint;

  @NotNull(message = "HTTP method for the endpoint is required")
  HttpMethod method;

  @NotNull(message = "Roles required to access the endpoint")
  List<String> roles;
}
