package com.example.chat.chat.person;

import com.example.chat.chat.conversation.Conversation;
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
    private List<Conversation> conversations = new ArrayList<>();

    public List<Conversation> getConversations() {
        return conversations;
    }

    public Person() {}
    public Person(Long id) {
        this.id = id;
    }
}
