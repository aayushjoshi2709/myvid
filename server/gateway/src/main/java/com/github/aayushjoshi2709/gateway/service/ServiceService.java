package com.github.aayushjoshi2709.gateway.service;

import com.github.aayushjoshi2709.gateway.dto.Endpoints.UpdateEndpointDto;
import com.github.aayushjoshi2709.gateway.dto.Service.CreateServiceDto;
import com.github.aayushjoshi2709.gateway.dto.Service.UpdateServiceDto;
import com.github.aayushjoshi2709.gateway.entity.Service;
import io.r2dbc.spi.Result;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.List;

@org.springframework.stereotype.Service
public interface ServiceService {

  Mono<Service> findById(UUID id);

  Mono<Service> findByName(String name);

  Mono<Service> create(CreateServiceDto csd);

  Mono<Service> update(UUID id, UpdateServiceDto csd);

  void delete(UUID id);

  Flux<Service> findAll();
}
