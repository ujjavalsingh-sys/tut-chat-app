package com.example.chat.chat.message;

import java.util.Optional;
import java.util.Set;

public record CreateMessageRequest(
    Optional<Long> conversationId,
    Optional<Set<Long>> participantIds,
    Long senderId,
    String text) {}
