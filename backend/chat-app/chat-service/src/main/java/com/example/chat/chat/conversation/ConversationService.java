package com.example.chat.chat.conversation;

import com.example.chat.chat.message.MessageService;
import com.example.chat.chat.person.PersonFullDto;
import com.example.chat.chat.person.PersonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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

  private List<PersonFullDto> getParticipants(Conversation conversation) {
    return conversation.getParticipantsIds().stream()
        .map(
            participantId -> {
              PersonFullDto dto = personService.findPersonFullDtoById(participantId);
              if (dto == null) {
                dto = new PersonFullDto(participantId, null, null);
              }
              return dto;
            })
        .toList();
  }

  private String getConversationName(Conversation conversation, Long authUserId) {
    List<Long> others =
        conversation.getParticipantsIds().stream()
            .filter(personId -> !personId.equals(authUserId))
            .toList();
    String conversationName = "";
    if (others.size() == 0) {
      PersonFullDto personFullDto = personService.findPersonFullDtoById(authUserId);
      conversationName = personFullDto.firstName() + " " + personFullDto.lastName();
    } else {
      List<String> othersNames =
          others.stream()
              .map(
                  personId -> {
                    PersonFullDto personFullDto = personService.findPersonFullDtoById(personId);
                    return personFullDto.firstName() + " " + personFullDto.lastName();
                  })
              .toList();
      conversationName = String.join(", ", othersNames);
    }
    return conversationName;
  }
}
