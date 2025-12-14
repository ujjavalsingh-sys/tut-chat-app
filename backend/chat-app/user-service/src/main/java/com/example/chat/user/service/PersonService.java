package com.example.chat.user.service;

import com.example.chat.user.entity.Person;
import com.example.chat.user.exception.PasswordMismatchException;
import com.example.chat.user.exception.PersonNotFoundException;
import com.example.chat.user.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person getUserByUsername(String username, String password) throws RuntimeException {
        Person person = personRepository.findByUsername(username)
                .orElseThrow(() -> new PersonNotFoundException(username + " not found"));
        if (!person.getPasswordHash().equals(password)) throw new PasswordMismatchException("Wrong password");
        return person;
    }

    public Person saveUser(Person person) {
        return personRepository.save(person);
    }

    public List<Person> getAllUsers() {
        return personRepository.findAll();
    }

    public Person getUserById(Long user_id) throws PersonNotFoundException {
        return personRepository.findById(user_id).orElseThrow(() -> new PersonNotFoundException("User not found"));
    }
}
