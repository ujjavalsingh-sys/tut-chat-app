package com.example.chat.chat.message;

public record MessageDto(
        Long messageId,
        String message,
        Long senderId,
        Long conversationId
) {
}
