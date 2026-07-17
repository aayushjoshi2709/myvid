package com.github.aayushjoshi2709.gateway.filter;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class RequestIdGenerationFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String requestId = UUID.randomUUID().toString();
        ServerHttpRequest request = exchange.getRequest().mutate().header("X-Request-ID", requestId).build();
        log.info("New request id generated: {}", requestId);
        ServerWebExchange mutatedExchange = exchange.mutate().request(request).build();
        return chain.filter(mutatedExchange);
    }
}
