package com.example.chat.chat.dto;

public record CreateMessageRequest(
        Long conversationId,
        Long senderId,
        String text
) {}
