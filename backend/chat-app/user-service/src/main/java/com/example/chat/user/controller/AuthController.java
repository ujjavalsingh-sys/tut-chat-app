package com.example.chat.user.controller;

import com.example.chat.user.dto.LoginRequest;
import com.example.chat.user.dto.NewPersonRequest;
import com.example.chat.user.dto.PersonDto;
import com.example.chat.user.entity.Person;
import com.example.chat.user.mapper.PersonMapper;
import com.example.chat.user.service.JwtService;
import com.example.chat.user.service.PersonService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  public static final int TOKEN_EXPIRY = 3600;
  private final PersonService personService;
  private final JwtService jwtService;

  public AuthController(PersonService personService, JwtService jwtService) {
    this.personService = personService;
    this.jwtService = jwtService;
  }

  @PostMapping("/register")
  public PersonDto registerUser(
      @RequestBody NewPersonRequest request, HttpServletResponse response) {
    Person newPerson =
        new Person(request.firstname(), request.lastname(), request.username(), request.password());
    Person person = personService.saveUser(newPerson);
    response.addCookie(createCookie(jwtService.generateToken(person.getId()), TOKEN_EXPIRY));
    return PersonMapper.toDto(person);
  }

  @PostMapping("/login")
  public PersonDto loginUser(@RequestBody LoginRequest request, HttpServletResponse response) {
    Person person = personService.getUserByUsername(request.username(), request.password());
    response.addCookie(createCookie(jwtService.generateToken(person.getId()), TOKEN_EXPIRY));
    return PersonMapper.toDto(person);
  }

  @PostMapping("/logout")
  public PersonDto logoutUser(HttpServletResponse response) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String token = authentication.getName();
    Long loggedInUserId = Long.valueOf(token);
    Person person = personService.getUserById(loggedInUserId);
    response.addCookie(createCookie(null, 0));
    SecurityContextHolder.getContext().setAuthentication(null);
    return PersonMapper.toDto(person);
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
}
