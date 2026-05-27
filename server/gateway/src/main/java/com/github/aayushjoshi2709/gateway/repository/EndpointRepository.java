package com.github.aayushjoshi2709.gateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.github.aayushjoshi2709.gateway.entity.Endpoint;
import com.github.aayushjoshi2709.gateway.entity.enums.Status;

@Repository
public interface EndpointRepository extends JpaRepository<Endpoint, Long> {
  Optional<Endpoint> findByIdAndStatus(Long id, Status status);

  Endpoint findOne(String serviceName, String sourceVersion, String sourceEndpoint);
}
