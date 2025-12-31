package com.example.chat.chat.conversation;

import com.example.chat.chat.message.MessageDto;
import com.example.chat.chat.person.PersonFullDto;

import java.util.List;

public record ConversationDto(
        Long conversationId,
        List<MessageDto> messages,
        List<PersonFullDto> participants
) {
}
