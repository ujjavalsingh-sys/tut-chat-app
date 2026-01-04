package com.example.chat.user.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtService {
  private final Key signKey;
  private final long expirationMs;

  public JwtService(String secret, long expirationMs) {
    signKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    this.expirationMs = expirationMs;
  }

  public String generateToken(Long userId) {
    return Jwts.builder()
        .setSubject(userId.toString())
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
        .signWith(signKey)
        .compact();
  }

  public Long getUserIdFromToken(String token) {
    final Claims claims =
        Jwts.parserBuilder().setSigningKey(signKey).build().parseClaimsJws(token).getBody();
    return Long.valueOf(claims.getSubject());
  }

  public void validateToken(String token) throws JwtException {
    Jwts.parserBuilder().setSigningKey(signKey).build().parseClaimsJws(token);
  }
}
