package com.github.aayushjoshi2709.gateway.controller;

import java.util.UUID;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.github.aayushjoshi2709.gateway.dto.Endpoints.CreateEndpointDto;
import com.github.aayushjoshi2709.gateway.dto.Endpoints.UpdateEndpointDto;
import com.github.aayushjoshi2709.gateway.entity.Endpoint;

import com.github.aayushjoshi2709.gateway.service.EndpointService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/endpoints")
@Validated
class EndpointController {

  private final EndpointService endpointService;

  EndpointController(
      final EndpointService endpointService) {
    this.endpointService = endpointService;
  }

  @GetMapping("/{id}")
  Mono<ResponseEntity<Endpoint>> getEndpoint(
      @Param("id") @NotNull(message = "Id cannot be null") @Positive(message = "Id must be a positive value") UUID id)
      throws ResponseStatusException {
    return this.endpointService.findById(id).map(ResponseEntity::ok);
  }

  @GetMapping
  Flux<Object> getEndpoints() {
    return this.endpointService.findAll().map(ResponseEntity::ok);
  }

  @PostMapping
  Mono<ResponseEntity<Endpoint>> createEndpoint(@Valid @RequestBody CreateEndpointDto body) {
    return this.endpointService.create(body).map(
        response -> ResponseEntity.status(HttpStatus.CREATED).body(response));
  }

  @PatchMapping
  Mono<ResponseEntity<Endpoint>> updateEndpoint(
      @Param("id") @NotNull(message = "Id cannot be null") @Positive(message = "Id must be a positive value") UUID id,
      @Valid @RequestBody UpdateEndpointDto body) {
    return this.endpointService.update(id, body).map(
        response -> ResponseEntity.ok(response));
  }

  @DeleteMapping("/{id}")
  ResponseEntity<Void> deleteEndpoint(
      @Param("id") @NotNull(message = "Id cannot be null") @Positive(message = "Id must be a positive value") UUID id)
      throws ResponseStatusException {
    this.endpointService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
