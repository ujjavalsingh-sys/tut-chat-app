package com.example.chat.chat.message;

import com.example.chat.chat.conversation.Conversation;
import com.example.chat.chat.person.Person;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    };

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private Person sender;

    public Person getSender() {
        return sender;
    }

    public Long getSenderId() {
        return sender.getId();
    }

    private String text;

    public String getText() {
        return text;
    }

    private LocalDateTime timestamp;

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    // Each message belongs to 1 conversation
    @ManyToOne
    // foreign key in Message table referring to the parent conversation primary attribute
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;

    public Conversation getConversation() {
        return conversation;
    }

    public Long parentConversationId() {
        return conversation.getId();
    }

    public Message() {}
    public Message(Person sender, String text, Conversation conversation) {
        this.sender = sender;
        this.text = text;
        this.conversation = conversation;
        this.timestamp = LocalDateTime.now();
    }
}
