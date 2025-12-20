package com.example.chat.chat.person;

import java.util.List;

public class PersonMapper {
    public static PersonDto toPersonDto(Person person) {
        return new PersonDto(person.getId());
    }

    public static List<PersonDto> toPersonDtoList(List<Person> persons) {
        return persons.stream().map(PersonMapper::toPersonDto).toList();
    }
}
