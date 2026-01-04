package com.example.chat.user.exception;

import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(PersonNotFoundException.class)
  public ResponseEntity<?> handleUserNotFoundException(PersonNotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(Map.of("error", "USER_NOT_FOUND", "message", e.getMessage()));
  }

  @ExceptionHandler(PasswordMismatchException.class)
  public ResponseEntity<?> handlePasswordMismatchException(PasswordMismatchException e) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(Map.of("error", "PASSWORD_MISMATCH", "message", e.getMessage()));
  }

  @ExceptionHandler(JwtException.class)
  public ResponseEntity<?> handleJwtException(JwtException e) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(
            Map.of(
                "error",
                "UNVERIFIED_ACCESS_TOKEN",
                "message",
                e.getMessage(),
                "source",
                "user-service"));
  }
}
