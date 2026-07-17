package com.github.aayushjoshi2709.gateway.filter;

import com.github.aayushjoshi2709.gateway.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.*;

@Component
@Order(2)
@RequiredArgsConstructor
@Slf4j
public class AuthenticationFilter implements WebFilter {

    private final JwtService jwtService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        HttpHeaders headers = exchange.getRequest().getHeaders();
        String authToken = headers.getFirst("Authorization");

        if(authToken != null && !authToken.isEmpty()) {
            log.info("Going to validate the access token: {}", authToken);
            String token = Objects.requireNonNull(authToken).substring(7);
            UUID userId = null;
            ArrayList<String> roles = null;
            try {
                userId = this.jwtService.getUserId(token);
                roles = this.jwtService.getClaims(token);
            } catch (Exception e){
                log.info("An error occurred while processing the access token: ", e);
                return Mono.error(
                        new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid auth token")
                );
            }
            log.debug("Access token validated successfully: {}", authToken);
            ServerHttpRequest request = exchange.getRequest().mutate()
                    .header("X-User-Id", userId.toString())
                    .header("X-User-Roles", roles.toString())
                    .build();
            ServerWebExchange mutatedExchange = exchange.mutate().request(request).build();
            return chain.filter(mutatedExchange);
        }
        return chain.filter(exchange);
    }
}
