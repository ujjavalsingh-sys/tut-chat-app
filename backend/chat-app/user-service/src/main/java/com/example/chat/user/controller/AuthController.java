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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
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
    response.addCookie(createCookie(jwtService.generateToken(person.getId())));
    return PersonMapper.toDto(person);
  }

  @PostMapping("/login")
  public PersonDto loginUser(@RequestBody LoginRequest request, HttpServletResponse response) {
    Person person = personService.getUserByUsername(request.username(), request.password());
    response.addCookie(createCookie(jwtService.generateToken(person.getId())));
    return PersonMapper.toDto(person);
  }

  private Cookie createCookie(String authToken) {
    Cookie cookie = new Cookie("access_token", authToken);
    cookie.setHttpOnly(true);
    cookie.setSecure(true);
    cookie.setPath("/");
    cookie.setMaxAge(3600);
    cookie.setAttribute("SameSite", "None");
    return cookie;
  }
}
