package com.example.chat.chat.conversation;

import com.example.chat.chat.message.Message;
import com.example.chat.chat.person.Person;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    public Long getId() {
        return id;
    }

    // 1 Conversation has many messages
    // mappedBy: bi-directional association with Messages entity
    // cascade: deleting a conversation deletes all messages associated to it
    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL, orphanRemoval = true)
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
    private Set<Person> participants = new HashSet<>();

    public List<Person> getParticipants() {
        return participants.stream().toList();
    }
    public void addParticipant(Person person) {
        participants.add(person);
    }
}
