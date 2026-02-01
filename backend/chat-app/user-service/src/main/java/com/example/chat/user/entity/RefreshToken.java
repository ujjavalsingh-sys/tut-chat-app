package com.example.chat.user.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class RefreshToken {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String token;

  String getToken() {
    return token;
  }

  @Column(nullable = false, updatable = false)
  private Instant createdAt;

  @Column(nullable = false)
  private Instant expiryDate;

  public Instant getExpiryDate() {
    return expiryDate;
  }

  @Column(nullable = false)
  private Long userId;

  public Long getUserId() {
    return userId;
  }

  public RefreshToken() {}

  public RefreshToken(String token, Long userId, Instant expiryDate) {
    this.token = token;
    this.userId = userId;
    this.expiryDate = expiryDate;
    this.createdAt = Instant.now();
  }
}
