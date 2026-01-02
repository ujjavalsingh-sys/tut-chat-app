package com.example.chat.chat.person;

import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PersonService {
  private final Map<Long, PersonFullDto> personCache = new ConcurrentHashMap<>();
  private final UserClient userClient;

  public PersonService(UserClient userClient) {
    this.userClient = userClient;
  }

  public PersonFullDto findPersonFullDtoById(Long id) {
    if (personCache.containsKey(id)) return personCache.get(id);
    try {
      PersonFullDto personFullDto = userClient.getUser(id);
      personCache.put(id, personFullDto);
      return personFullDto;
    } catch (FeignException.NotFound e) {
      return null;
    }
  }
}
