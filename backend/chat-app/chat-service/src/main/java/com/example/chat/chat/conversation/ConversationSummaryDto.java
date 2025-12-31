package com.example.chat.chat.conversation;

import com.example.chat.chat.person.PersonDto;

import java.util.List;

public record ConversationSummaryDto(
        Long conversationId,
        Integer messageCount,
        List<PersonDto> participants
) {
}
