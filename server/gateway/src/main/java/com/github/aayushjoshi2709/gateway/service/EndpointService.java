package com.github.aayushjoshi2709.gateway.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.github.aayushjoshi2709.gateway.repository.EndpointRepository;
import com.github.aayushjoshi2709.gateway.dto.Endpoints.CreateEndpointDto;
import com.github.aayushjoshi2709.gateway.dto.Endpoints.UpdateEndpointDto;
import com.github.aayushjoshi2709.gateway.entity.Endpoint;
import com.github.aayushjoshi2709.gateway.entity.enums.Status;
import com.github.aayushjoshi2709.gateway.mapper.Endpoint.CreateEndpointDtoMapper;

import java.util.List;
import java.util.Map;

@Service
public class EndpointService {
  private final EndpointRepository endpointRepo;
  private final CreateEndpointDtoMapper createEndpointDtoMapper;

  EndpointService(
      final EndpointRepository endpointRepo,
      final CreateEndpointDtoMapper createEndpointDtoMapper) {
    this.endpointRepo = endpointRepo;
    this.createEndpointDtoMapper = createEndpointDtoMapper;
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
    Endpoint endpoint = createEndpointDtoMapper.toEntity(body);
    this.endpointRepo.save(endpoint);
    return Map.of("messsage", "Endpoint created successfully");
  }

  public void delete(Long id) throws ResponseStatusException {
    Endpoint endpoint = this.findById(id);
    endpoint.setStatus(Status.INACTIVE);
    this.endpointRepo.save(endpoint);
  }

  public Endpoint update(Long id, UpdateEndpointDto body) throws ResponseStatusException {
    Endpoint e = this.findById(id);

    String serviceName = body.getServiceName();
    String sourceEndpoint = body.getSourceEndpoint();
    String destEndpoint = body.getTargetEndpoint();
    String sourceVersion = body.getSourceVersion();
    String targetVersion = body.getTargetVersion();

    if (serviceName != null) {
      e.setServiceName(serviceName);
    }

    if (sourceEndpoint != null) {
      e.setSourceEndpoint(sourceEndpoint);
    }

    if (destEndpoint != null) {
      e.setTargetEndpoint(destEndpoint);
    }

    if (targetVersion != null) {
      e.setTargetVersion(targetVersion);
    }

    if (sourceVersion != null) {
      e.setSourceVersion(sourceVersion);
    }

    return this.endpointRepo.save(e);
  }
}
