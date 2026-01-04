package com.example.chat.user.mapper;

import com.example.chat.user.dto.PersonDto;
import com.example.chat.user.entity.Person;
import java.util.List;

public class PersonMapper {
  public static PersonDto toDto(Person person) {
    return new PersonDto(
        person.getId(), person.getUsername(), person.getFirstName(), person.getLastName());
  }

  public static List<PersonDto> toDtoList(List<Person> persons) {
    return persons.stream().map(PersonMapper::toDto).toList();
  }
}
