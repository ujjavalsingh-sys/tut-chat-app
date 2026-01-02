package com.example.chat.chat.conversation;

import com.example.chat.chat.message.Message;
import com.example.chat.chat.message.MessageMapper;
import com.example.chat.chat.person.PersonDto;

import java.util.List;

public class ConversationMapper {
  public static ConversationDto toConversationDto(
      Conversation conversation,
      String conversationName,
      List<PersonDto> personDtos,
      Message latestMessage) {
    return new ConversationDto(
        conversation.getId(), conversationName, MessageMapper.toDto(latestMessage), personDtos);
  }
}
