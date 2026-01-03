package com.example.gateway.config;

import com.example.gateway.service.JwtService;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@Configuration
public class JwtFilterConfig {
  private final JwtService jwtService;

  public JwtFilterConfig(JwtService jwtService) {
    this.jwtService = jwtService;
  }

  @Bean
  public GlobalFilter jwtValidationFilter() {
    return ((exchange, chain) -> {
      String path = exchange.getRequest().getURI().getPath();

      if (path.startsWith("/api/users/login") || path.startsWith("/api/users/register")) {
        return chain.filter(exchange);
      }

      String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
      if (authHeader != null && authHeader.startsWith("Bearer ")) {
        String token = authHeader.substring(7);

        if (!jwtService.validateToken(token)) {
          exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
          return exchange.getResponse().setComplete();
        }
      } else {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
      }

      return chain.filter(exchange);
    });
  }
}
