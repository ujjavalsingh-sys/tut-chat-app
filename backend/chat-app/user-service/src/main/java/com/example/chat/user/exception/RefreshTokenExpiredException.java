package com.example.chat.user.exception;

public class RefreshTokenExpiredException extends RuntimeException {
  public RefreshTokenExpiredException() {
    super("Refresh token has expired. Please log in again.");
  }
}
