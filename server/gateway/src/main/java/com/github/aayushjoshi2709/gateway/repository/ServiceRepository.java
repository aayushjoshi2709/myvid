package com.github.aayushjoshi2709.gateway.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.github.aayushjoshi2709.gateway.entity.Service;

@Repository
public interface ServiceRepository extends ReactiveCrudRepository<Service, Long> {
}
