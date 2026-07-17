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
  public Mono<Endpoint> findById(UUID id);

  public Flux<Endpoint> findAll();

  public Mono<Endpoint> create(CreateEndpointDto body);

  public Mono<Void> delete(UUID id);

  public Mono<Endpoint> update(UUID id, UpdateEndpointDto body);
}
