package com.github.aayushjoshi2709.gateway.controller;

import java.util.UUID;

import com.github.aayushjoshi2709.gateway.service.EndpointService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.github.aayushjoshi2709.gateway.dto.Endpoints.CreateEndpointDto;
import com.github.aayushjoshi2709.gateway.dto.Endpoints.UpdateEndpointDto;
import com.github.aayushjoshi2709.gateway.entity.Endpoint;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/endpoints")
@RequiredArgsConstructor
@Validated
class EndpointController {
  private final EndpointService endpointService;

  @GetMapping("/{id}")
  Mono<ResponseEntity<Endpoint>> getEndpoint(
          @PathVariable @NotNull(message = "Id cannot be null") UUID id)
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

  @PatchMapping("/{id}")
  Mono<ResponseEntity<Endpoint>> updateEndpoint(
          @PathVariable @NotNull(message = "Id cannot be null") UUID id,
      @Valid @RequestBody UpdateEndpointDto body) {
    return this.endpointService.update(id, body).map(
            ResponseEntity::ok);
  }

  @DeleteMapping("/{id}")
  ResponseEntity<Void> deleteEndpoint(
          @PathVariable @NotNull(message = "Id cannot be null") UUID id)
      throws ResponseStatusException {
    this.endpointService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
