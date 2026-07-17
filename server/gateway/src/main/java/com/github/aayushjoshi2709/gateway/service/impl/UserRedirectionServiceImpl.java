package com.github.aayushjoshi2709.gateway.service.impl;

import com.github.aayushjoshi2709.gateway.service.EndpointService;
import com.github.aayushjoshi2709.gateway.service.ServiceService;
import com.github.aayushjoshi2709.gateway.service.UserRedirectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserRedirectionServiceImpl implements UserRedirectionService {
  private final EndpointService endpointService;
  private final ServiceService serviceService;

  @Override
  public Mono<Void> handleRedirection(ServerWebExchange exchange, WebFilterChain chain) {
    log.info("Inside request redirection service handle redirection function");
    return chain.filter(exchange);
  }
}
