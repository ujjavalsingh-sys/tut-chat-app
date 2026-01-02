package com.example.chat.chat.message;

import com.example.chat.chat.conversation.Conversation;
import com.example.chat.chat.conversation.ConversationRepository;
import com.example.chat.chat.conversation.ConversationService;
import com.example.chat.chat.person.PersonService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MessageService {
  private final MessageRepository messageRepository;
  private final ConversationRepository conversationRepository;

  public MessageService(
      MessageRepository messageRepository, ConversationRepository conversationRepository) {
    this.messageRepository = messageRepository;
    this.conversationRepository = conversationRepository;
  }

  @Transactional
  public Message sendMessage(
      Optional<Long> conversationId,
      Optional<Set<Long>> participantIds,
      Long senderId,
      String text) {
    Conversation conversation =
        conversationId.isPresent()
            ? conversationRepository.findById(conversationId.get()).orElse(null)
            : conversationRepository.save(new Conversation(participantIds.get()));

    Message message = new Message(senderId, text, conversation);
    return messageRepository.save(message);
  }

  public List<Message> getMessages(Long conversationId) {
    return messageRepository.findByConversationId(conversationId);
  }

  public Message getLatestMessage(Long conversationId) {
    return messageRepository.findFirstByConversationId_OrderByTimestampDesc(conversationId);
  }
}
