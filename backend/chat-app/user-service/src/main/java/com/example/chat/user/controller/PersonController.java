package com.example.chat.user.controller;

import com.example.chat.user.entity.Person;
import com.example.chat.user.service.JwtService;
import com.example.chat.user.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class PersonController {
  private final PersonService personService;
  private final JwtService jwtService;

  public PersonController(PersonService personService, JwtService jwtService) {
    this.personService = personService;
    this.jwtService = jwtService;
  }

  @GetMapping
  public List<Person> getAllUsers() {
    return personService.getAllUsers();
  }

  @GetMapping("/{userId}")
  public Person getUserById(@PathVariable("userId") Long userId) {
    return personService.getUserById(userId);
  }
}
