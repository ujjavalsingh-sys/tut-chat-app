package com.example.gateway.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class GlobalErrorHandler implements ErrorWebExceptionHandler {

  @Override
  public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
    ServerHttpResponse response = exchange.getResponse();
    response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

    String errorType;

    if (ex instanceof JwtException) {
      response.setStatusCode(HttpStatus.UNAUTHORIZED);
      errorType = "UNVERIFIED_ACCESS_TOKEN";
    } else {
      response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
      errorType = "INTERNAL_SERVER_ERROR";
    }

    Map<String, String> map =
        Map.of("error", errorType, "message", ex.getMessage(), "source", "gateway");
    String message = null;
    try {
      message = new ObjectMapper().writeValueAsString(map);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    DataBuffer buffer = response.bufferFactory().wrap(message.getBytes(StandardCharsets.UTF_8));

    return response.writeWith(Mono.just(buffer));
  }
}
