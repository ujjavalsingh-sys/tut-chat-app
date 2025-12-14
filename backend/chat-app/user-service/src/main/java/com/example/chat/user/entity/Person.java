package com.example.chat.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    private String username;
    public String getUsername() {
        return username;
    }

    private String passwordHash;
    public String getPasswordHash() {
        return passwordHash;
    }

    public Person() {}
    public Person(String username, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
    }
}
