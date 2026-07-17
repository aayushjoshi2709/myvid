package com.github.aayushjoshi2709.gateway.service.impl;

import com.github.aayushjoshi2709.gateway.service.EndpointService;
import com.github.aayushjoshi2709.gateway.service.ServiceService;
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
import java.util.UUID;

@Service
public class EndpointServiceImpl implements EndpointService {
  private final EndpointRepository endpointRepo;
  private final CreateEndpointDtoMapper createEndpointDtoMapper;
  private final ServiceService serviceService;

  EndpointServiceImpl(
      final EndpointRepository endpointRepo,
      final CreateEndpointDtoMapper createEndpointDtoMapper,
      final ServiceService serviceService) {
    this.endpointRepo = endpointRepo;
    this.createEndpointDtoMapper = createEndpointDtoMapper;
    this.serviceService = serviceService;
  }

  public Mono<Endpoint> findById(UUID id) throws ResponseStatusException {
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

  public Mono<Void> delete(UUID id) {
    return findById(id)
        .flatMap(endpoint -> {
          endpoint.setStatus(Status.INACTIVE);
          return endpointRepo.save(endpoint);
        })
        .then();
  }

  public Mono<Endpoint> update(UUID id, UpdateEndpointDto body) {

    return findById(id)
        .flatMap(endpoint -> {

          Mono<Endpoint> endpointMono;

          if (body.getServiceId() != null) {
            endpointMono = this.serviceService.findById(body.getServiceId())
                .map(service -> {
                  endpoint.setServiceId(service.getId());
                  return endpoint;
                });
          } else {
            endpointMono = Mono.just(endpoint);
          }

          return endpointMono.flatMap(e -> {

            Optional.ofNullable(body.getSourceEndpoint())
                .ifPresent(e::setSourceEndpoint);

            Optional.ofNullable(body.getTargetEndpoint())
                .ifPresent(e::setTargetEndpoint);

            Optional.ofNullable(body.getSourceVersion())
                .ifPresent(e::setSourceVersion);

            Optional.ofNullable(body.getTargetVersion())
                .ifPresent(e::setTargetVersion);

            return endpointRepo.save(e);
          });
        });
  }
}
