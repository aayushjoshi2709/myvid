package com.github.aayushjoshi2709.gateway.service.impl;

import java.util.List;
import java.util.UUID;

import com.github.aayushjoshi2709.gateway.dto.Service.UpdateServiceDto;
import com.github.aayushjoshi2709.gateway.entity.enums.Status;
import com.github.aayushjoshi2709.gateway.service.ServiceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.github.aayushjoshi2709.gateway.entity.Service;
import com.github.aayushjoshi2709.gateway.repository.ServiceRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.github.aayushjoshi2709.gateway.dto.Service.CreateServiceDto;
import com.github.aayushjoshi2709.gateway.mapper.Service.CreateServiceDtoMapper;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

  private final ServiceRepository serviceRepository;
  private final CreateServiceDtoMapper createServiceDtoMapper;

  public ServiceServiceImpl(final ServiceRepository serviceRepository,
                            final CreateServiceDtoMapper createServiceDtoMapper) {
    this.serviceRepository = serviceRepository;
    this.createServiceDtoMapper = createServiceDtoMapper;
  }

  public Mono<Service> findById(UUID id) {
    return this.serviceRepository.findById(id).switchIfEmpty(Mono.error(
        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Service not found with the given service id")));
  }

  @Override
  public Mono<Service> findByName(String name) {
    return this.serviceRepository.findByServiceName(name).switchIfEmpty(Mono.error(
            () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Service not found with the given service name")));
  }

  public Mono<Service> create(CreateServiceDto csd) {
    Service svc = createServiceDtoMapper.toEntity(csd);
    return this.serviceRepository.save(svc);
  }

  @Override
  public Mono<Service> update(UUID id, UpdateServiceDto csd) {
    return null;
  }

  @Override
  public void delete(UUID id) {
    findById(id)
            .flatMap(service -> {
              service.setStatus(Status.INACTIVE);
              return serviceRepository.save(service);
            }).subscribe();
  }

  @Override
  public Flux<Service> findAll() {
    return this.serviceRepository.findAll();
  }
}
