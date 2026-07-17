package com.github.aayushjoshi2709.gateway.repository;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.github.aayushjoshi2709.gateway.entity.Service;
import reactor.core.publisher.Mono;

@Repository
public interface ServiceRepository extends ReactiveCrudRepository<Service, UUID> {
    Mono<Service> findByServiceName(String name);
}
