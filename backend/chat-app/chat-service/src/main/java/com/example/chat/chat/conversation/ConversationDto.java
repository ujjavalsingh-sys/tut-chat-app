package com.example.chat.chat.conversation;

import com.example.chat.chat.message.MessageDto;
import com.example.chat.chat.person.PersonDto;

import java.util.List;

public record ConversationDto(
    Long conversationId,
    String conversationName,
    MessageDto latestMessage,
    List<PersonDto> participants) {}
