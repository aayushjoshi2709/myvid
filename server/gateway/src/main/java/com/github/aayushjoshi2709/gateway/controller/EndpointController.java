package com.github.aayushjoshi2709.gateway.controller;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import java.util.Map;

import com.github.aayushjoshi2709.gateway.dto.Endpoints.CreateEndpointDto;
import com.github.aayushjoshi2709.gateway.dto.Endpoints.UpdateEndpointDto;
import com.github.aayushjoshi2709.gateway.entity.Endpoint;

import com.github.aayushjoshi2709.gateway.service.EndpointService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Controller
@RequestMapping("/endpoints")
@Validated
class EndpointController {

  private final EndpointService endpointService;

  EndpointController(
      final EndpointService endpointService) {
    this.endpointService = endpointService;
  }

  @GetMapping("/{id}")
  ResponseEntity<Endpoint> getEndpoint(
      @Param("id") @NotNull(message = "Id cannot be null") @Positive(message = "Id must be a positive value") Long id)
      throws ResponseStatusException {
    Endpoint endpoint = this.endpointService.findById(id);
    return ResponseEntity.ok(endpoint);
  }

  @GetMapping
  ResponseEntity<List<Endpoint>> getEndpoints() {
    return ResponseEntity.ok(this.endpointService.findAll());
  }

  @PostMapping
  ResponseEntity<Map<String, String>> createEndpoint(@Valid @RequestBody CreateEndpointDto body) {
    return ResponseEntity.status(HttpStatus.CREATED).body(this.endpointService.create(body));
  }

  @PatchMapping
  ResponseEntity<Endpoint> updateEndpoint(
      @Param("id") @NotNull(message = "Id cannot be null") @Positive(message = "Id must be a positive value") Long id,
      @Valid @RequestBody UpdateEndpointDto body) {
    return ResponseEntity.ok(this.endpointService.update(id, body));
  }

  @DeleteMapping("/{id}")
  ResponseEntity<Void> deleteEndpoint(
      @Param("id") @NotNull(message = "Id cannot be null") @Positive(message = "Id must be a positive value") Long id)
      throws ResponseStatusException {
    this.endpointService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
