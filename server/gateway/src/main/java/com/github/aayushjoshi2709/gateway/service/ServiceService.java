package com.github.aayushjoshi2709.gateway.service;

import com.github.aayushjoshi2709.gateway.dto.Service.CreateServiceDto;
import com.github.aayushjoshi2709.gateway.entity.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@org.springframework.stereotype.Service
public interface ServiceService {

  public Mono<Service> findById(UUID id);

  public Mono<Service> create(CreateServiceDto csd);
}
