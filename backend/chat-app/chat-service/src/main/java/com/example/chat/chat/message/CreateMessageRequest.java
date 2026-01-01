package com.example.chat.chat.message;

import com.example.chat.chat.person.PersonDto;

import java.util.List;
import java.util.Optional;

public record CreateMessageRequest(
        Optional<Long> conversationId,
        Optional<List<PersonDto>> participantIds,
        Long senderId,
        String text
) {}
