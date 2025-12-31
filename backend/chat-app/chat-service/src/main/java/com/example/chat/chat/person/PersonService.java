package com.example.chat.chat.person;

import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final Map<Long, PersonFullDto> personCache =  new ConcurrentHashMap<>();
    private final UserClient userClient;

    public PersonService(PersonRepository personRepository, UserClient userClient) {
        this.personRepository = personRepository;
        this.userClient = userClient;
    }

    public Person findPersonById(Long id) {
        return personRepository.findById(id).orElse(null);
    }
    public Person createPerson(Long id) {
        Person person = new Person(id);
        return personRepository.save(person);
    }

    public PersonFullDto findPersonFullDtoById(Long id) {
        if (personCache.containsKey(id))
            return personCache.get(id);
        try {
            PersonFullDto personFullDto = userClient.getUser(id);
            personCache.put(id, personFullDto);
            return personFullDto;
        }
        catch (FeignException.NotFound e) {
            return null;
        }
    }
}
