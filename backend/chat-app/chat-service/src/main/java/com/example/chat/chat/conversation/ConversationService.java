package com.example.chat.chat.conversation;

import com.example.chat.chat.message.MessageService;
import com.example.chat.chat.person.PersonDto;
import com.example.chat.chat.person.PersonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ConversationService {
  private final ConversationRepository conversationRepository;
  private final PersonService personService;
  private final MessageService messageService;

  public ConversationService(
      ConversationRepository conversationRepository,
      PersonService personService,
      MessageService messageService) {
    this.conversationRepository = conversationRepository;
    this.personService = personService;
    this.messageService = messageService;
  }

  public List<ConversationDto> getConversationDtosByUserId(Long userId) {
    List<Conversation> conversations = conversationRepository.findByParticipantsIds(userId);
    return conversations.stream()
        .map(conversation -> toConversationDto(conversation, userId))
        .toList();
  }

  public ConversationDto getConversationDtoById(Long id, Long authUserId) {
    Conversation conversation = conversationRepository.findById(id).orElse(null);
    return toConversationDto(conversation, authUserId);
  }

  private ConversationDto toConversationDto(Conversation conversation, Long authUserId) {
    return ConversationMapper.toConversationDto(
        conversation,
        getConversationName(conversation, authUserId),
        getParticipants(conversation),
        messageService.getLatestMessage(conversation.getId()));
  }

  private List<PersonDto> getParticipants(Conversation conversation) {
    return conversation.getParticipantsIds().stream()
        .map(personService::fetchPersonDtoById)
        .toList();
  }

  private String getConversationName(Conversation conversation, Long authUserId) {
    List<Long> others =
        conversation.getParticipantsIds().stream()
            .filter(personId -> !Objects.equals(personId, authUserId))
            .toList();
    String conversationName = "";
    if (others.isEmpty()) {
      PersonDto personDto = personService.fetchPersonDtoById(authUserId);
      conversationName = personDto.firstName() + " " + personDto.lastName();
    } else {
      List<String> othersNames =
          others.stream()
              .map(
                  personId -> {
                    PersonDto personDto = personService.fetchPersonDtoById(personId);
                    return personDto.firstName() + " " + personDto.lastName();
                  })
              .toList();
      conversationName = String.join(", ", othersNames);
    }
    return conversationName;
  }
}
