package com.example.chat.user.controller;

import com.example.chat.user.dto.LoginRequest;
import com.example.chat.user.dto.NewPersonRequest;
import com.example.chat.user.dto.PersonDto;
import com.example.chat.user.entity.Person;
import com.example.chat.user.entity.RefreshToken;
import com.example.chat.user.exception.RefreshTokenExpiredException;
import com.example.chat.user.mapper.PersonMapper;
import com.example.chat.user.service.JwtService;
import com.example.chat.user.service.PersonService;
import com.example.chat.user.service.RefreshTokenService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final PersonService personService;
  private final JwtService jwtService;
  private final RefreshTokenService refreshTokenService;

  @Value("${jwt.expirationSec}")
  private int jwtExpirationSec;

  @Value("${jwt.refreshExpirationSec}")
  private int refreshTokenExpirationSec;

  public AuthController(
      PersonService personService, JwtService jwtService, RefreshTokenService refreshTokenService) {
    this.personService = personService;
    this.jwtService = jwtService;
    this.refreshTokenService = refreshTokenService;
  }

  @PostMapping("/register")
  public PersonDto registerUser(
      @RequestBody NewPersonRequest request, HttpServletResponse response) {
    Person newPerson =
        new Person(request.firstname(), request.lastname(), request.username(), request.password());
    Person person = personService.saveUser(newPerson);
    response.addCookie(createCookie(jwtService.generateToken(person.getId()), jwtExpirationSec));
    return PersonMapper.toDto(person);
  }

  @PostMapping("/login")
  public PersonDto loginUser(@RequestBody LoginRequest request, HttpServletResponse response) {
    Person person = personService.getUserByUsername(request.username(), request.password());

    response.addCookie(createCookie(jwtService.generateToken(person.getId()), jwtExpirationSec));

    String refreshToken = UUID.randomUUID().toString();
    response.addCookie(refreshTokenCookie(refreshToken, refreshTokenExpirationSec));

    refreshTokenService.save(
        refreshToken, person.getId(), Instant.now().plusSeconds(refreshTokenExpirationSec));

    return PersonMapper.toDto(person);
  }

  @PostMapping("/logout")
  public PersonDto logoutUser(HttpServletRequest request, HttpServletResponse response) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String authUserId = authentication.getName();
    Long loggedInUserId = Long.valueOf(authUserId);
    Person person = personService.getUserById(loggedInUserId);

    response.addCookie(createCookie(null, 0));

    response.addCookie((refreshTokenCookie(null, 0)));

    String refreshToken = extractRefreshToken(request);
    refreshTokenService.delete(refreshToken);

    SecurityContextHolder.getContext().setAuthentication(null);

    return PersonMapper.toDto(person);
  }

  @PostMapping("/refresh")
  public ResponseEntity<?> refreshAccessToken(
      HttpServletRequest request, HttpServletResponse response) {
    String refreshToken = extractRefreshToken(request);
    RefreshToken token = refreshTokenService.validate(refreshToken);

    response.addCookie(createCookie(jwtService.generateToken(token.getUserId()), jwtExpirationSec));

    return ResponseEntity.ok(Map.of("status", "ok"));
  }

  private String extractRefreshToken(HttpServletRequest request) {
    if (request.getCookies() == null) {
      throw new RefreshTokenExpiredException();
    }
    return Arrays.stream(request.getCookies())
        .filter(cookie -> cookie.getName().equals("refresh_token"))
        .map(Cookie::getValue)
        .findFirst()
        .orElseThrow(RefreshTokenExpiredException::new);
  }

  private Cookie createCookie(String authToken, int expiry) {
    Cookie cookie = new Cookie("access_token", authToken);
    cookie.setHttpOnly(true);
    cookie.setSecure(true);
    cookie.setPath("/");
    cookie.setMaxAge(expiry);
    cookie.setAttribute("SameSite", "None");
    return cookie;
  }

  private Cookie refreshTokenCookie(String refreshToken, int expiry) {
    Cookie cookie = new Cookie("refresh_token", refreshToken);
    cookie.setHttpOnly(true);
    cookie.setSecure(true);
    cookie.setPath("/api/auth");
    cookie.setMaxAge(expiry);
    cookie.setAttribute("SameSite", "None");
    return cookie;
  }
}
