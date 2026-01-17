package com.example.chat.user.config;

import com.example.chat.user.filter.JwtAuthFilter;
import com.example.chat.user.service.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
  private final JwtService jwtService;

  public SecurityConfig(JwtService jwtService) {
    this.jwtService = jwtService;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
        .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(
            authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/api/auth/login", "/api/auth/register", "/error")
                    .permitAll()
                    .anyRequest()
                    .authenticated())
        .addFilterBefore(new JwtAuthFilter(jwtService), UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
}
