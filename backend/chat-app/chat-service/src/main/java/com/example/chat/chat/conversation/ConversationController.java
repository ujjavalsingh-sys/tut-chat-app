package com.example.chat.chat.conversation;

import com.example.chat.chat.person.Person;
import com.example.chat.chat.person.PersonFullDto;
import com.example.chat.chat.person.PersonService;
import org.springframework.web.bind.annotation.*;

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
        return  ConvesationMapper.toConversationSummaryDtoList(conversationService.getConversationsByUserId(participantId));
    }
}
