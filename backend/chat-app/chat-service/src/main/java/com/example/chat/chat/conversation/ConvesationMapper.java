package com.example.chat.chat.conversation;

import com.example.chat.chat.message.MessageMapper;
import com.example.chat.chat.person.PersonFullDto;
import com.example.chat.chat.person.PersonMapper;

import java.util.List;

public class ConvesationMapper {
    public static ConversationDto toConversationDto(Conversation conversation, List<PersonFullDto> personFullDtos) {
        return new ConversationDto(
                conversation.getId(),
                MessageMapper.toDtoList(conversation.getMessages()),
                personFullDtos
        );
    }

    public static ConversationSummaryDto toConversationSummaryDto(Conversation conversation) {
        return new ConversationSummaryDto(
                conversation.getId(),
                conversation.getMessages().size(),
                PersonMapper.toPersonDtoList(conversation.getParticipants())
        );
    }

    public static List<ConversationSummaryDto> toConversationSummaryDtoList(
            List<Conversation> conversations
    ) {
        return conversations.stream().map(ConvesationMapper::toConversationSummaryDto).toList();
    }
}
