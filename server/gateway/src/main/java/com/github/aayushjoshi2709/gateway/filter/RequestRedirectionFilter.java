package com.github.aayushjoshi2709.gateway.filter;

import com.github.aayushjoshi2709.gateway.service.UserRedirectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@Component
@Order(3)
@RequiredArgsConstructor
@Slf4j
class RequestRedirectionFilter implements WebFilter {

  private final UserRedirectionService userRedirectionService;

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

    log.info("In request redirection filter redirecting" );
    String path = exchange.getRequest().getURI().getPath();
    List<String> splitPath = List.of(path.split("/"));
    String service = splitPath.get(3);

    log.info("Redirecting request to {} service", service);
    if(service != null && !Objects.equals(service, "gateway")) {
      return this.userRedirectionService.handleRedirection(exchange, chain);
    } else {
      return chain.filter(exchange);
    }
  }
}
