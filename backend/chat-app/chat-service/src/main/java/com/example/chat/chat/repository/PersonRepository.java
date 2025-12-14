package com.example.chat.chat.repository;

import com.example.chat.chat.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
