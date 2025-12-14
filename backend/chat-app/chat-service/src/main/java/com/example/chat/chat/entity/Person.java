package com.example.chat.chat.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Person {
    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    @ManyToMany(mappedBy = "participants")
    @JsonManagedReference
    private Set<Conversation> conversations = new HashSet<>();

    public Set<Conversation> getConversations() {
        return conversations;
    }

    public Person() {}
    public Person(Long id) {
        this.id = id;
    }
}
