package com.example.chat.user.controller;

import com.example.chat.user.dto.AuthResponse;
import com.example.chat.user.dto.LoginRequest;
import com.example.chat.user.dto.NewPersonRequest;
import com.example.chat.user.entity.Person;
import com.example.chat.user.service.JwtService;
import com.example.chat.user.service.PersonService;
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
  public AuthResponse registerUser(@RequestBody NewPersonRequest request) {
    Person newPerson =
        new Person(request.firstname(), request.lastname(), request.username(), request.password());
    Person person = personService.saveUser(newPerson);
    return new AuthResponse(jwtService.generateToken(person.getId()), person);
  }

  @PostMapping("/login")
  public AuthResponse loginUser(@RequestBody LoginRequest request) {
    Person person = personService.getUserByUsername(request.username(), request.password());
    return new AuthResponse(jwtService.generateToken(person.getId()), person);
  }
}
