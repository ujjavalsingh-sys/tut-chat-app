package com.example.chat.chat.conversation;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConversationService {
    private final ConversationRepository conversationRepository;
    public ConversationService(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    public Conversation createConversation() {
        Conversation conversation = new Conversation();
        return conversationRepository.save(conversation);
    }
    public List<Conversation> getAllConversations() {
        return conversationRepository.findAll();
    }

    public Conversation getConversationById(Long id) {
        return conversationRepository
                .findById(id)
                .orElse(null);
    }
}
