package com.example.chat.chat.conversation;

import com.example.chat.chat.person.Person;
import com.example.chat.chat.person.PersonFullDto;
import com.example.chat.chat.person.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/conversations")
public class ConversationController {
    private final ConversationService conversationService;
    private final PersonService personService;
    public ConversationController(ConversationService conversationService, PersonService personService) {
        this.conversationService = conversationService;
        this.personService = personService;
    }

    @PostMapping
    public Conversation createConversation() {
        return conversationService.createConversation();
    }

    @GetMapping("/{id}")
    public ConversationDto getConversationById(@PathVariable("id") Long id) {
        Conversation conversation = conversationService.getConversationById(id);
        List<Person> participants = conversation.getParticipants();
        List<PersonFullDto> personFullDtos = participants.stream().map(person -> {
            PersonFullDto dto = personService.findPersonFullDtoById(person.getId());
            if (dto == null) {
                dto = new PersonFullDto(person.getId(), null,  null);
            }
            return dto;
        }).toList();
        return ConvesationMapper.toConversationDto(conversationService.getConversationById(id), personFullDtos);
    }

    @GetMapping()
    public List<ConversationSummaryDto> getConversations(@RequestParam("participant") Long participantId) {
        List<Conversation> conversations = conversationService.getConversationsByUserId(participantId);
        List<ConversationSummaryDto> conversationSummaryDtos = new ArrayList<>();
        for (Conversation conversation : conversations) {
            List<Person> others = conversation.getParticipants().stream().filter(person -> !person.getId().equals(participantId)).toList();
            String conversationName = "";
            if (others.size() == 0) {
                PersonFullDto personFullDto = personService.findPersonFullDtoById(participantId);
                conversationName = personFullDto.firstName() + " " + personFullDto.lastName();
            }
            else  {
                List<String> othersNames = others.stream().map(person -> {
                    PersonFullDto personFullDto = personService.findPersonFullDtoById(person.getId());
                    return personFullDto.firstName() + " " + personFullDto.lastName();
                }).toList();
                conversationName = String.join(", ", othersNames);
            }
            conversationSummaryDtos.add(ConvesationMapper.toConversationSummaryDto(conversation, conversationName));
        }
        return conversationSummaryDtos;
    }
}
