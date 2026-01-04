package com.example.gateway.service;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

public class JwtService {
  private final Key key;

  public JwtService(String secret) {
    this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
  }

  public void validateToken(String token) throws JwtException {
    Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
  }
}
