package com.github.aayushjoshi2709.gateway.filter;

import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@Order(1)
public class RequestIdGenerationFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
            ServerHttpRequest request = exchange.getRequest().mutate().header("X-Request-Id", UUID.randomUUID().toString()).build();
            ServerWebExchange mutatedExchange = exchange.mutate().request(request).build();
            return chain.filter(mutatedExchange);
    }
}
