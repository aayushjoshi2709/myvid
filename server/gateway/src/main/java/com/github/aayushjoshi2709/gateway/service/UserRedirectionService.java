package com.github.aayushjoshi2709.gateway.service;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Service
public interface UserRedirectionService {
    Mono<Void> handleRedirection(ServerWebExchange exchange, WebFilterChain chain);
}
