package com.github.aayushjoshi2709.gateway.service.impl;

import com.github.aayushjoshi2709.gateway.entity.Endpoint;
import com.github.aayushjoshi2709.gateway.service.EndpointService;
import com.github.aayushjoshi2709.gateway.service.ServiceService;
import com.github.aayushjoshi2709.gateway.service.UserRedirectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserRedirectionServiceImpl implements UserRedirectionService {

  private final EndpointService endpointService;
  private final ServiceService serviceService;
  private final WebClient.Builder webClientBuilder;

  private static final Pattern PATH_VARIABLE = Pattern.compile("\\{[^{}/]+}");

  private boolean matchesPath(String requestPath, Endpoint endpoint) {
    String template = "/api/" + endpoint.getSourceVersion() + endpoint.getSourceEndpoint();

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


  private boolean validateRequest(ServerWebExchange exchange, Endpoint endpoint){
    String header = exchange.getRequest()
            .getHeaders()
            .getFirst("X-User-Roles");

    List<String> userRoles = List.of();

    if (header != null && !header.isBlank()) {
      userRoles = Arrays.stream(
                      header.substring(1, header.length() - 1)
                              .split(",\\s*"))
              .toList();
    }
    return satisfyRole(endpoint.getRoles(), userRoles);
  }

  private Mono<Void> prepareClientResponse(ServerWebExchange exchange, ClientResponse clientResponse){
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
            clientResponse.bodyToFlux(DataBuffer.class)
    );
  }

  private Mono<Void> getResponseFromDownStreamService(
          String targetUrl,
          ServerWebExchange exchange
  ){
    WebClient webClient = webClientBuilder.build();

    return webClient
            .method(exchange.getRequest().getMethod())
            .uri(URI.create(targetUrl))
            .headers(headers -> {
              headers.addAll(exchange.getRequest().getHeaders());
              headers.remove(HttpHeaders.HOST);
              headers.remove(HttpHeaders.CONTENT_LENGTH);
            })
            .body(BodyInserters.fromDataBuffers(exchange.getRequest().getBody()))
            .exchangeToMono(clientResponse -> {
              return prepareClientResponse(exchange, clientResponse);
            });
  }

  @Override
  public Mono<Void> handleRedirection(String serviceName, ServerWebExchange exchange) {

    log.info("Redirecting request to service {}", serviceName);

    String requestPath = exchange.getRequest().getURI().getRawPath();

    return serviceService.findByName(serviceName)
            .switchIfEmpty(Mono.error(
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Service not found")))
            .flatMap(service ->
                    endpointService.findByServiceId(service.getId())
                            .filter(endpoint -> matchesPath(requestPath, endpoint))
                            .next()
                            .switchIfEmpty(Mono.error(
                                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Endpoint not found")))
                            .flatMap(endpoint -> {

                              if(!validateRequest(exchange, endpoint)){
                                  return Mono.error(new ResponseStatusException(
                                          HttpStatus.FORBIDDEN,
                                          "You do not have proper authorization to access this route"));
                              }

                              String targetUrl = service.getServiceUrl()
                                      + exchange.getRequest().getURI().getRawPath();

                              if (exchange.getRequest().getURI().getRawQuery() != null) {
                                targetUrl += "?" + exchange.getRequest().getURI().getRawQuery();
                              }

                              return getResponseFromDownStreamService(targetUrl, exchange);
                            })
            );
  }
}