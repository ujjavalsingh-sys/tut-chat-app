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

    private String firstName;
    public String getFirstName() {
        return firstName;
    }

    private String lastName;
    public String getLastName() {
        return lastName;
    }

    public Person() {}
    public Person(String firstName, String lastName, String username, String passwordHash) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.passwordHash = passwordHash;
    }
}
