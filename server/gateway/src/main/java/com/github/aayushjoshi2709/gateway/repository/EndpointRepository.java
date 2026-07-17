package com.github.aayushjoshi2709.gateway.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import com.github.aayushjoshi2709.gateway.entity.Endpoint;
import com.github.aayushjoshi2709.gateway.entity.enums.Status;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;
@Repository
public interface EndpointRepository extends ReactiveCrudRepository<Endpoint, UUID> {
  Mono<Endpoint> findByIdAndStatus(UUID id, Status status);

  Flux<Endpoint> findByServiceIdAndStatus(UUID serviceId, Status status);
}
