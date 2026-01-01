package com.example.chat.chat.message;

import com.example.chat.chat.conversation.Conversation;
import com.example.chat.chat.conversation.ConversationService;
import com.example.chat.chat.person.Person;
import com.example.chat.chat.person.PersonDto;
import com.example.chat.chat.person.PersonService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Transactional
    public Message sendMessage(Optional<Long> conversationId, Optional<List<PersonDto>> participantIds, Long senderId, String text) {
        Conversation conversation = conversationId.map(conversationService::getConversationById).orElse(null);
        if (conversation == null) {
            conversation = conversationService.createConversation();
            if (participantIds.isPresent()) {
                for (PersonDto participantId: participantIds.get()) {
                    Long personId = participantId.personId();
                    Person participant = personService.findPersonById(personId);
                    if (participant == null) {
                        participant = personService.createPerson(personId);
                    }
                    conversation.addParticipant(participant);
                    participant.getConversations().add(conversation);
                };
            }
        }
        Person sender = personService.findPersonById(senderId);
        if (sender == null) {
            sender = personService.createPerson(senderId);
        }
        Message message = new Message(sender, text, conversation);
        return messageRepository.save(message);
    }

    public List<Message> getMessages(Long conversationId) {
        return messageRepository.findByConversationId(conversationId);
    }
}
