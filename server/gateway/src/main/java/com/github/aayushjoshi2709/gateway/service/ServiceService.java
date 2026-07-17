package com.github.aayushjoshi2709.gateway.service;

import com.github.aayushjoshi2709.gateway.dto.Service.CreateServiceDto;
import com.github.aayushjoshi2709.gateway.entity.Service;
import com.github.aayushjoshi2709.gateway.mapper.Service.CreateServiceDtoMapper;
import com.github.aayushjoshi2709.gateway.repository.ServiceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.UUID;

@org.springframework.stereotype.Service
public interface ServiceService {

  public Mono<Service> findById(UUID id);

  public Mono<Service> create(CreateServiceDto csd);
}
