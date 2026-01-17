package com.example.gateway.config;

import com.example.gateway.service.JwtService;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpCookie;
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

      if (path.equals("/api/auth/register") || path.equals("/api/auth/login")) {
        return chain.filter(exchange);
      }

      HttpCookie cookie = exchange.getRequest().getCookies().getFirst("access_token");
      if (cookie != null) {
        String token = cookie.getValue();
        jwtService.validateToken(token);
      } else {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
      }

      return chain.filter(exchange);
    });
  }
}
