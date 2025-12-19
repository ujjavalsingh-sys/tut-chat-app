package com.example.chat.chat.message;

public record CreateMessageRequest(
        Long conversationId,
        Long senderId,
        String text
) {}
