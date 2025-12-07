package com.example.chat.chat.service;

import com.example.chat.chat.entity.Conversation;
import com.example.chat.chat.entity.Message;
import com.example.chat.chat.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final ConversationService conversationService;
    public MessageService(MessageRepository messageRepository,
                          ConversationService conversationService) {
        this.messageRepository = messageRepository;
        this.conversationService = conversationService;
    }

    public Message sendMessage(Long conversationId, Long senderId, String text) {
        Conversation conversation = conversationService.getConversationById(conversationId);
        Message message = new Message(senderId, text, conversation);
        return messageRepository.save(message);
    }

    public List<Message> getMessages(Long conversationId) {
        return messageRepository.findByConversationId(conversationId);
    }
}
