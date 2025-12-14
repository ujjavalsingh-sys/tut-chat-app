package com.example.chat.user.repository;

import com.example.chat.user.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    public Optional<Person> findByUsername(String username);
}
