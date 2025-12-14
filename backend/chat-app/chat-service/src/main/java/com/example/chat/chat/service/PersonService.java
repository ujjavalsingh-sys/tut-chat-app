package com.example.chat.chat.service;

import com.example.chat.chat.entity.Conversation;
import com.example.chat.chat.entity.Person;
import com.example.chat.chat.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    public Person findPersonById(Long id) {
        return personRepository.findById(id).orElse(null);
    }
    public Person createPerson(Long id) {
        Person person = new Person(id);
        return personRepository.save(person);
    }
}
