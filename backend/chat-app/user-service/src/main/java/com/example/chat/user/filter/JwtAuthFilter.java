package com.example.chat.user.filter;

import com.example.chat.user.service.JwtService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Collections;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {
  private final JwtService jwtService;

  public JwtAuthFilter(JwtService jwtService) {
    this.jwtService = jwtService;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    if (request.getCookies() != null) {
      for (Cookie cookie : request.getCookies()) {
        if (cookie.getName().equals("access_token")) {
          String token = cookie.getValue();
          try {
            jwtService.validateToken(token);
          } catch (JwtException e) {
            break;
          }

          Long userId = jwtService.getUserIdFromToken(token);

          UsernamePasswordAuthenticationToken authToken =
              new UsernamePasswordAuthenticationToken(userId, null, Collections.emptyList());

          SecurityContextHolder.getContext().setAuthentication(authToken);
          break;
        }
      }
    }

    filterChain.doFilter(request, response);
  }
}
