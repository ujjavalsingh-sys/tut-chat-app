package com.example.chat.chat.conversation;

import com.example.chat.chat.message.Message;
import com.example.chat.chat.message.MessageMapper;
import com.example.chat.chat.person.PersonFullDto;

import java.util.List;

public class ConversationMapper {
  public static ConversationDto toConversationDto(
      Conversation conversation,
      String conversationName,
      List<PersonFullDto> personFullDtos,
      Message latestMessage) {
    return new ConversationDto(
        conversation.getId(), conversationName, MessageMapper.toDto(latestMessage), personFullDtos);
  }
}
