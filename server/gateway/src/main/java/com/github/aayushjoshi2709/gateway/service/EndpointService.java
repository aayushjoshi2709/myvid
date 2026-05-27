package com.github.aayushjoshi2709.gateway.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.github.aayushjoshi2709.gateway.repository.EndpointRepository;
import com.github.aayushjoshi2709.gateway.dto.Endpoints.CreateEndpointDto;
import com.github.aayushjoshi2709.gateway.entity.Endpoint;
import com.github.aayushjoshi2709.gateway.entity.enums.Status;

import java.util.List;
import java.util.Map;

@Service
public class EndpointService {
  private final EndpointRepository endpointRepo;

  EndpointService(final EndpointRepository endpointRepo) {
    this.endpointRepo = endpointRepo;
  }

  public Endpoint findById(Long id) throws ResponseStatusException {
    Endpoint endpoint = this.endpointRepo.findByIdAndStatus(id, Status.ACTIVE)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Endpoint not found"));
    return endpoint;
  }

  public List<Endpoint> findAll() {
    return this.endpointRepo.findAll();
  }

  public Map<String, String> create(CreateEndpointDto body) {

    return Map.of("messsage", "Endpoint created successfully");
  }

  public void delete(Long id) throws ResponseStatusException {
    Endpoint endpoint = this.findById(id);
    endpoint.setStatus(Status.INACTIVE);
    this.endpointRepo.save(endpoint);
  }
}
