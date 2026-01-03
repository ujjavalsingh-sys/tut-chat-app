package com.example.chat.user.config;

import com.example.chat.user.service.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class JwtConfig {
  @Bean
  public JwtService jwtService(
      @Value("${jwt.secret}") String secret, @Value("${jwt.expirationMs}") long expirationMs) {
    return new JwtService(secret, expirationMs);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
