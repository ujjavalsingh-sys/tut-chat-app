package com.example.chat.chat.message;

import java.time.LocalDateTime;

public record MessageDto(
    Long messageId,
    String message,
    LocalDateTime creationDate,
    Long senderId,
    Long conversationId) {}
