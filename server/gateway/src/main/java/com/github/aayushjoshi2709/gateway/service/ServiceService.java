package com.github.aayushjoshi2709.gateway.service;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.github.aayushjoshi2709.gateway.entity.Service;
import com.github.aayushjoshi2709.gateway.repository.ServiceRepository;
import reactor.core.publisher.Mono;
import com.github.aayushjoshi2709.gateway.dto.Service.CreateServiceDto;
import com.github.aayushjoshi2709.gateway.mapper.Service.CreateServiceDtoMapper;

@org.springframework.stereotype.Service
public class ServiceService {

  private final ServiceRepository serviceRepository;
  private final CreateServiceDtoMapper createServiceDtoMapper;

  public ServiceService(final ServiceRepository serviceRepository,
      final CreateServiceDtoMapper createServiceDtoMapper) {
    this.serviceRepository = serviceRepository;
    this.createServiceDtoMapper = createServiceDtoMapper;
  }

  public Mono<Service> findById(UUID id) {
    return this.serviceRepository.findById(id).switchIfEmpty(Mono.error(
        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Service not found with the given service id")));
  }

  public Mono<Service> create(CreateServiceDto csd) {
    Service svc = createServiceDtoMapper.toEntity(csd);
    return this.serviceRepository.save(svc);
  }
}
