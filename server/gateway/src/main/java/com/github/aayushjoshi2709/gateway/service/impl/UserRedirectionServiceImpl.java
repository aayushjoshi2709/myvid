package com.github.aayushjoshi2709.gateway.service.impl;

import com.github.aayushjoshi2709.gateway.entity.Endpoint;
import com.github.aayushjoshi2709.gateway.service.EndpointService;
import com.github.aayushjoshi2709.gateway.service.JwtService;
import com.github.aayushjoshi2709.gateway.service.ServiceService;
import com.github.aayushjoshi2709.gateway.service.UserRedirectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserRedirectionServiceImpl implements UserRedirectionService {

  @Value("${appdata.defaults.roles}")
  private String defaultRoles;
  private final EndpointService endpointService;
  private final ServiceService serviceService;
  private final WebClient.Builder webClientBuilder;
  private final JwtService jwtService;

  private static final Pattern PATH_VARIABLE = Pattern.compile("\\{[^{}/]+}");

  private boolean matchesPath(String requestPath, Endpoint endpoint) {
    String template = "/api/" + endpoint.getEndpoint();

    Matcher variableMatcher = PATH_VARIABLE.matcher(template);
    StringBuilder regex = new StringBuilder();
    int lastEnd = 0;

    while (variableMatcher.find()) {
      regex.append(Pattern.quote(template.substring(lastEnd, variableMatcher.start())));
      regex.append("[^/]+");
      lastEnd = variableMatcher.end();
    }
    regex.append(Pattern.quote(template.substring(lastEnd)));

    return Pattern.matches(regex.toString(), requestPath);
  }

  private boolean satisfyRole(List<String> endpointRoles, List<String> userRoles) {
    if (endpointRoles == null || endpointRoles.isEmpty()) {
      return true;
    }

    if (userRoles == null || userRoles.isEmpty()) {
      return false;
    }

    return userRoles.stream().anyMatch(endpointRoles::contains);
  }

  private boolean validateRequest(ServerWebExchange exchange, Endpoint endpoint) {
    String header = exchange.getRequest()
        .getHeaders()
        .getFirst("x-user-roles");

    List<String> userRoles = List.of();

    if (header != null && !header.isBlank()) {
      userRoles = Arrays.stream(
          header.substring(1, header.length() - 1)
              .split(",\\s*"))
          .toList();
    }
    return satisfyRole(endpoint.getRoles(), userRoles);
  }

  private String prepareTargetUri(URI uri, String serviceUrl) {
    String targetUrl = serviceUrl
        + uri.getRawPath();

    if (uri.getRawQuery() != null) {
      targetUrl += "?" + uri.getRawQuery();
    }
    return targetUrl;
  }

  private Mono<Void> prepareClientResponse(ServerWebExchange exchange, ClientResponse clientResponse) {
    exchange.getResponse()
        .setStatusCode(clientResponse.statusCode());

    clientResponse.headers()
        .asHttpHeaders()
        .forEach((name, values) -> {
          if (!HttpHeaders.TRANSFER_ENCODING.equalsIgnoreCase(name)
              && !HttpHeaders.CONTENT_LENGTH.equalsIgnoreCase(name)) {
            exchange.getResponse()
                .getHeaders()
                .put(name, values);
          }
        });

    return exchange.getResponse().writeWith(
        clientResponse.bodyToFlux(DataBuffer.class));
  }

  private Mono<Void> getResponseFromDownStreamService(
      String targetUrl,
      ServerWebExchange exchange) {
    return webClientBuilder.build()
        .method(exchange.getRequest().getMethod())
        .uri(URI.create(targetUrl))
        .headers(headers -> {
          headers.addAll(exchange.getRequest().getHeaders());
          headers.remove(HttpHeaders.HOST);
          headers.remove(HttpHeaders.CONTENT_LENGTH);
        })
        .body(BodyInserters.fromDataBuffers(exchange.getRequest().getBody()))
        .exchangeToMono(clientResponse -> prepareClientResponse(exchange, clientResponse));
  }

  void extractUserDetails(ServerWebExchange exchange) {
      HttpHeaders headers = exchange.getRequest().getHeaders();
      String authToken = headers.getFirst("Authorization");
      String userId ="";
      List<String> roles = new ArrayList<>(List.of(defaultRoles.split(",")));

      if (authToken != null && !authToken.isEmpty()) {
        log.info("Going to validate the access token: {}", authToken);
        String token = Objects.requireNonNull(authToken).substring(7);
        try {
          userId = this.jwtService.getUserId(token).toString();
          roles.addAll(this.jwtService.getRoles(token));
        } catch (Exception e) {
          log.info("An error while processing the access token: ", e);
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid auth token");
        }

        if (userId == null) {
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid auth token");
        }
      }


      log.debug("Access token validated successfully: {}", authToken);
      log.debug("Here are roles: {}", roles);
      ServerHttpRequest request = exchange.getRequest().mutate()
              .header("x-user-id", userId)
              .header("x-user-roles", roles.toString())
              .build();
      exchange.mutate().request(request).build();
  }

  @Override
  public Mono<Void> handleRedirection(String serviceName, ServerWebExchange exchange) {

    log.info("Redirecting request to service {}", serviceName);
    String requestPath = exchange.getRequest().getURI().getRawPath();

    return this.serviceService.findByName(serviceName)
        .switchIfEmpty(Mono.error(
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Service not found")))
        .flatMap(service -> endpointService.findByServiceId(service.getId())
            .filter(endpoint -> endpoint.getMethod().name().equals(
                exchange.getRequest().getMethod().name())
                && matchesPath(requestPath, endpoint))
            .next()
            .switchIfEmpty(Mono.error(
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Endpoint not found")))
            .flatMap(endpoint -> {
              this.extractUserDetails(exchange);
              if (!validateRequest(exchange, endpoint)) {
                return Mono.error(new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "You do not have proper authorization to access this route"));
              }

              String targetUri = this.prepareTargetUri(exchange.getRequest().getURI(), service.getServiceUrl());
              return this.getResponseFromDownStreamService(targetUri, exchange);
            }));
  }
}
