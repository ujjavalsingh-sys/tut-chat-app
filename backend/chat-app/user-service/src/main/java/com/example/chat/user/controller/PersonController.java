package com.example.chat.user.controller;

import com.example.chat.user.dto.PersonDto;
import com.example.chat.user.entity.Person;
import com.example.chat.user.mapper.PersonMapper;
import com.example.chat.user.service.JwtService;
import com.example.chat.user.service.PersonService;
import org.springframework.security.core.Authentication;
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
  public List<PersonDto> getAllUsers() {
    return PersonMapper.toDtoList(personService.getAllUsers());
  }

  @GetMapping("/{userId}")
  public PersonDto getUserById(@PathVariable("userId") Long userId) {
    return PersonMapper.toDto(personService.getUserById(userId));
  }

  @GetMapping("/me")
  public PersonDto getCurrentUser(Authentication authentication) {
    Long userId = Long.valueOf(authentication.getName());
    return PersonMapper.toDto(personService.getUserById(userId));
  }
}
