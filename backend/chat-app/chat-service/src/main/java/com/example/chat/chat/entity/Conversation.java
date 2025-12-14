package com.example.chat.chat.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("conversationId")
    @Column(name = "id")
    private Long id;

    public Long getId() {
        return id;
    }

    // 1 Conversation has many messages
    // mappedBy: bi-directional association with Messages entity
    // cascade: deleting a conversation deletes all messages associated to it
    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Message> messages = new ArrayList<>();

    public List<Message> getMessages() {
        return messages;
    }

    @ManyToMany
    @JoinTable(
            name = "conversation_participants",
            joinColumns = @JoinColumn(name = "conversation_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    @JsonIgnore
    private Set<Person> participants = new HashSet<>();

    public Set<Person> getParticipants() {
        return participants;
    }

    @JsonProperty("participantsIds")
    public List<Long> getParticipantsIds() {
        return participants.stream().map(person -> person.getId()).toList();
    }
}
