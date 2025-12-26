package com.example.chat.user.controller;

import com.example.chat.user.dto.LoginRequest;
import com.example.chat.user.dto.NewPersonRequest;
import com.example.chat.user.entity.Person;
import com.example.chat.user.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class PersonController {
    private final PersonService personService;
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/register")
    public Person registerUser(@RequestBody NewPersonRequest request) {
        Person person = new Person(request.firstname(), request.lastname(), request.username(), request.password());
        return personService.saveUser(person);
    }

    @PostMapping("/login")
    public Person loginUser(@RequestBody LoginRequest request) {
        Person person = personService.getUserByUsername(request.username(), request.password());
        return person;
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
