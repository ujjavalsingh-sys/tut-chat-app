package com.example.chat.chat.person;

import org.springframework.stereotype.Service;

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
