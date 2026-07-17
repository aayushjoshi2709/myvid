package com.github.aayushjoshi2709.gateway.controller;

import com.github.aayushjoshi2709.gateway.dto.Endpoints.CreateEndpointDto;
import com.github.aayushjoshi2709.gateway.dto.Endpoints.UpdateEndpointDto;
import com.github.aayushjoshi2709.gateway.dto.Service.CreateServiceDto;
import com.github.aayushjoshi2709.gateway.dto.Service.UpdateServiceDto;
import com.github.aayushjoshi2709.gateway.entity.Endpoint;
import com.github.aayushjoshi2709.gateway.entity.Service;
import com.github.aayushjoshi2709.gateway.service.ServiceService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController("/services")
@Validated
@RequiredArgsConstructor
public class ServiceController {
    private final ServiceService serviceService;


    @GetMapping("/{id}")
    Mono<ResponseEntity<Service>> getEndpoint(
            @Param("id") @NotNull(message = "Id cannot be null") @Positive(message = "Id must be a positive value") UUID id)
            throws ResponseStatusException {
        return this.serviceService.findById(id).map(ResponseEntity::ok);
    }

    @GetMapping
    Flux<Object> getEndpoints() {
        return this.serviceService.findAll().map(ResponseEntity::ok);
    }

    @PostMapping
    Mono<ResponseEntity<Service>> createEndpoint(@Valid @RequestBody CreateServiceDto body) {
        return this.serviceService.create(body).map(
                response -> ResponseEntity.status(HttpStatus.CREATED).body(response));
    }

    @PatchMapping
    Mono<ResponseEntity<Service>> updateEndpoint(
            @Param("id") @NotNull(message = "Id cannot be null") @Positive(message = "Id must be a positive value") UUID id,
            @Valid @RequestBody UpdateServiceDto body) {
        return this.serviceService.update(id, body).map(
                ResponseEntity::ok);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteEndpoint(
            @Param("id") @NotNull(message = "Id cannot be null") @Positive(message = "Id must be a positive value") UUID id)
            throws ResponseStatusException {
        this.serviceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
