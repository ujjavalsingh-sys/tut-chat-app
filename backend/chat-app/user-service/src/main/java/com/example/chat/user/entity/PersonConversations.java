package com.example.chat.user.entity;

import jakarta.persistence.*;

@Entity
public class PersonConversations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    @ManyToOne(fetch = FetchType.LAZY) // User is fetched on demand
    @JoinColumn(name = "chat_user_id")
    private Person person;

    public Person getPerson() {
        return person;
    }

    private Long conversationId;

    public Long getConversationId() {
        return conversationId;
    }

    public PersonConversations() {}
    public PersonConversations(Person person, Long conversationId) {
        this.person = person;
        this.conversationId = conversationId;
    }
}
