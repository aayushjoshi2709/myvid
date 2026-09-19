package com.github.aayushjoshi2709.gateway.handlers;

import com.github.aayushjoshi2709.gateway.dto.Error.ErrorResponse;
import org.jspecify.annotations.NullMarked;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;
import tools.jackson.databind.ObjectMapper;

@Component
@Order(-2)
public class GlobalWebExceptionHandler implements WebExceptionHandler {

    private final ObjectMapper objectMapper;
    public GlobalWebExceptionHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    @NullMarked
    public Mono<Void> handle(
            ServerWebExchange exchange,
            Throwable ex) {

        HttpStatusCode status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = ex.getMessage();

        if (ex instanceof ResponseStatusException e) {
            message = e.getReason();
        }

        ErrorResponse errorResponse = new ErrorResponse(message);

        byte[] bytes;

        bytes = objectMapper.writeValueAsBytes(errorResponse);
        var response = exchange.getResponse();
        response.setStatusCode(status);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        return response.writeWith(
                Mono.just(
                        response.bufferFactory().wrap(bytes)
                )
        );
    }
}