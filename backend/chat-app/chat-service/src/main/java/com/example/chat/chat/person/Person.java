package com.example.chat.chat.person;

import com.example.chat.chat.conversation.Conversation;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
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
