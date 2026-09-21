package com.github.aayushjoshi2709.authservice.handlers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, String>> handleException(
            ResponseStatusException ex
    ) {
        assert ex.getReason() != null;
        return ResponseEntity
                .status(ex.getStatusCode())
                .body(Map.of(
                        "message", ex.getReason()
                ));
    }
}