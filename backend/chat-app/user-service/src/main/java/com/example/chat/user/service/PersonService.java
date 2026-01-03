package com.example.chat.user.service;

import com.example.chat.user.entity.Person;
import com.example.chat.user.exception.PasswordMismatchException;
import com.example.chat.user.exception.PersonNotFoundException;
import com.example.chat.user.repository.PersonRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
  private final PersonRepository personRepository;
  private final PasswordEncoder passwordEncoder;

  public PersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
    this.personRepository = personRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public Person getUserByUsername(String username, String password) throws RuntimeException {
    Person person =
        personRepository
            .findByUsername(username)
            .orElseThrow(() -> new PersonNotFoundException(username + " not found"));
    if (!passwordEncoder.matches(password, person.getPasswordHash()))
      throw new PasswordMismatchException("Wrong password");
    return person;
  }

  public Person saveUser(Person person) {
    person.setPasswordHash(passwordEncoder.encode(person.getPasswordHash()));
    return personRepository.save(person);
  }

  public List<Person> getAllUsers() {
    return personRepository.findAll();
  }

  public Person getUserById(Long user_id) throws PersonNotFoundException {
    return personRepository
        .findById(user_id)
        .orElseThrow(() -> new PersonNotFoundException("User not found: " + user_id));
  }
}
