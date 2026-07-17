package com.github.aayushjoshi2709.gateway.service;

import com.github.aayushjoshi2709.gateway.dto.Endpoints.CreateEndpointDto;
import com.github.aayushjoshi2709.gateway.dto.Endpoints.UpdateEndpointDto;
import com.github.aayushjoshi2709.gateway.entity.Endpoint;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public interface EndpointService {
  Mono<Endpoint> findById(UUID id);

  Flux<Endpoint> findAll();

  Mono<Endpoint> create(CreateEndpointDto body);

  void delete(UUID id);

  Mono<Endpoint> update(UUID id, UpdateEndpointDto body);
}
