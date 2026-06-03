
package com.github.aayushjoshi2709.gateway.dto.Endpoints;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class UpdateEndpointDto {
  @Size(min = 1, message = "Service name should have min length of 1")
  String serviceName;

  @Size(min = 1, message = "Service name should have min length of 1")
  String sourceVersion;

  @Size(min = 1, message = "Service name should have min length of 1")
  String sourceEndpoint;

  @Size(min = 1, message = "Service name should have min length of 1")
  String targetVersion;

  @Size(min = 1, message = "Service name should have min length of 1")
  String targetEndpoint;
}
