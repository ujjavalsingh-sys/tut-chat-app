package com.example.chat.chat.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Long for now; later it will be integrated via user-service
    private Long senderId;

    private String text;

    private LocalDateTime timestamp;

    // Each message belongs to 1 conversation
    @ManyToOne
    // foreign key in Message table referring to the parent conversation primary attribute
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;

    public Message() {}
    public Message(Long senderId, String text, Conversation conversation) {
        this.senderId = senderId;
        this.text = text;
        this.conversation = conversation;
        this.timestamp = LocalDateTime.now();
    }
}
