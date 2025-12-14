package com.example.chat.chat.service;

import com.example.chat.chat.entity.Conversation;
import com.example.chat.chat.entity.Message;
import com.example.chat.chat.entity.Person;
import com.example.chat.chat.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final ConversationService conversationService;
    private final PersonService personService;
    public MessageService(MessageRepository messageRepository,
                          ConversationService conversationService,
                          PersonService personService) {
        this.messageRepository = messageRepository;
        this.conversationService = conversationService;
        this.personService = personService;
    }

    public Message sendMessage(Long conversationId, Long senderId, String text) {
        Conversation conversation = conversationService.getConversationById(conversationId);
        if (conversation == null) {
            conversation = conversationService.createConversation();
        }
        Person sender = personService.findPersonById(senderId);
        if (sender == null) {
            sender = personService.createPerson(senderId);
        }
        sender.getConversations().add(conversation);
        conversation.getParticipants().add(sender);
        Message message = new Message(sender, text, conversation);
        return messageRepository.save(message);
    }

    public List<Message> getMessages(Long conversationId) {
        return messageRepository.findByConversationId(conversationId);
    }
}
