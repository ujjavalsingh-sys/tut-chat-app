package com.example.chat.chat.conversation;

public record ConversationSummaryDto(
        Long conversationId,
        Integer messageCount
) {
}
