package com.github.aayushjoshi2709.gateway.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.github.aayushjoshi2709.gateway.repository.EndpointRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.github.aayushjoshi2709.gateway.dto.Endpoints.CreateEndpointDto;
import com.github.aayushjoshi2709.gateway.dto.Endpoints.UpdateEndpointDto;
import com.github.aayushjoshi2709.gateway.entity.Endpoint;
import com.github.aayushjoshi2709.gateway.entity.enums.Status;
import com.github.aayushjoshi2709.gateway.mapper.Endpoint.CreateEndpointDtoMapper;

import java.util.Optional;

@Service
public class EndpointService {
  private final EndpointRepository endpointRepo;
  private final CreateEndpointDtoMapper createEndpointDtoMapper;

  EndpointService(
      final EndpointRepository endpointRepo,
      final CreateEndpointDtoMapper createEndpointDtoMapper) {
    this.endpointRepo = endpointRepo;
    this.createEndpointDtoMapper = createEndpointDtoMapper;
  }

  public Mono<Endpoint> findById(Long id) throws ResponseStatusException {
    return this.endpointRepo.findByIdAndStatus(id, Status.ACTIVE)
        .switchIfEmpty(
            Mono.error(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Endpoint not found")));
  }

  public Flux<Endpoint> findAll() {
    return this.endpointRepo.findAll();
  }

  public Mono<Endpoint> create(CreateEndpointDto body) {
    Endpoint endpoint = createEndpointDtoMapper.toEntity(body);
    return this.endpointRepo.save(endpoint);
  }

  public Mono<Void> delete(Long id) {
    return findById(id)
        .flatMap(endpoint -> {
          endpoint.setStatus(Status.INACTIVE);
          return endpointRepo.save(endpoint);
        })
        .then();
  }

  public Mono<Endpoint> update(Long id, UpdateEndpointDto body) throws ResponseStatusException {
    return findById(id)
        .flatMap(endpoint -> {

          Optional.ofNullable(body.getServiceName())
              .ifPresent(endpoint::setServiceName);

          Optional.ofNullable(body.getSourceEndpoint())
              .ifPresent(endpoint::setSourceEndpoint);

          Optional.ofNullable(body.getTargetEndpoint())
              .ifPresent(endpoint::setTargetEndpoint);

          Optional.ofNullable(body.getSourceVersion())
              .ifPresent(endpoint::setSourceVersion);

          Optional.ofNullable(body.getTargetVersion())
              .ifPresent(endpoint::setTargetVersion);

          return endpointRepo.save(endpoint);
        });
  }
}
