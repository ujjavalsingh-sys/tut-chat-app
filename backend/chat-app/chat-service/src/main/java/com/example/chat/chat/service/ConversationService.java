package com.example.chat.chat.service;

import com.example.chat.chat.entity.Conversation;
import com.example.chat.chat.repository.ConversationRepository;
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
                .orElseThrow(() -> new RuntimeException("Conversation with id " + id + " not found"));
    }
}
