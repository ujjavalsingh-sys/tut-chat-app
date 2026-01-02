package com.example.chat.chat.person;

import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PersonService {
  private final Map<Long, PersonDto> personCache = new ConcurrentHashMap<>();
  private final UserClient userClient;

  public PersonService(UserClient userClient) {
    this.userClient = userClient;
  }

  public PersonDto fetchPersonDtoById(Long id) {
    if (personCache.containsKey(id)) return personCache.get(id);
    try {
      PersonDto personDto = userClient.getUser(id);
      personCache.put(id, personDto);
      return personDto;
    } catch (FeignException.NotFound e) {
      return null;
    } catch (Exception e) {
      return new PersonDto(id, "_unavailable_", "_unavailable_");
    }
  }
}
