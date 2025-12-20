package com.example.chat.chat.message;

import java.util.List;

public class MessageMapper {
    public static MessageDto toDto(Message message) {
        return new MessageDto(
                message.getId(),
                message.getText(),
                message.getSenderId(),
                message.parentConversationId()
        );
    }

    public static List<MessageDto> toDtoList(List<Message> messages) {
        return messages.stream().map(MessageMapper::toDto).toList();
    }
}
