package com.github.aayushjoshi2709.gateway.service;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Service
public interface UserRedirectionService {
    Mono<Void> handleRedirection(String serviceName, ServerWebExchange exchange);
}
