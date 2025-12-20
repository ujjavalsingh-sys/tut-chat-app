package com.example.chat.chat.conversation;

import com.example.chat.chat.message.MessageMapper;
import com.example.chat.chat.person.PersonMapper;

import java.util.List;

public class ConvesationMapper {
    public static ConversationDto toConversationDto(Conversation conversation) {
        return new ConversationDto(
                conversation.getId(),
                MessageMapper.toDtoList(conversation.getMessages()),
                PersonMapper.toPersonDtoList(conversation.getParticipants())
        );
    }

    public static ConversationSummaryDto toConversationSummaryDto(Conversation conversation) {
        return new ConversationSummaryDto(
                conversation.getId(),
                conversation.getMessages().size()
        );
    }

    public static List<ConversationSummaryDto> toConversationSummaryDtoList(
            List<Conversation> conversations
    ) {
        return conversations.stream().map(ConvesationMapper::toConversationSummaryDto).toList();
    }
}
