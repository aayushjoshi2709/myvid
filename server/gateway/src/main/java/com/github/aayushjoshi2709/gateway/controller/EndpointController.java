package com.github.aayushjoshi2709.gateway.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import java.util.Map;

import com.github.aayushjoshi2709.gateway.dto.Endpoints.CreateEndpointDto;
import com.github.aayushjoshi2709.gateway.entity.Endpoint;

import com.github.aayushjoshi2709.gateway.service.EndpointService;

@Controller
@RequestMapping("/endpoints")
class EndpointController {

  private final EndpointService endpointService;

  EndpointController(
      final EndpointService endpointService) {
    this.endpointService = endpointService;
  }

  @GetMapping("/{id}")
  ResponseEntity<Endpoint> getEndpoint(@PathVariable Long id) throws ResponseStatusException {
    Endpoint endpoint = this.endpointService.findById(id);
    return ResponseEntity.ok(endpoint);
  }

  @GetMapping
  ResponseEntity<List<Endpoint>> getEndpoints() {
    return ResponseEntity.ok(this.endpointService.findAll());
  }

  @PostMapping
  ResponseEntity<Map<String, String>> createEndpoint(@RequestBody CreateEndpointDto body) {
    return ResponseEntity.ok(this.endpointService.create(body));
  }

  @DeleteMapping("/{id}")
  ResponseEntity<Void> deleteEndpoint(@PathVariable Long id) throws ResponseStatusException {
    this.endpointService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
