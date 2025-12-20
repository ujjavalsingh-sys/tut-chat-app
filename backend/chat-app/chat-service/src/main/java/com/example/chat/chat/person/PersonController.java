package com.example.chat.chat.person;

import com.example.chat.chat.conversation.ConversationSummaryDto;
import com.example.chat.chat.conversation.ConvesationMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class PersonController {
    private final PersonService personService;
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{userId}")
    public PersonDto getPerson(@PathVariable("userId") Long userId) {
        return PersonMapper.toPersonDto(personService.findPersonById(userId));
    }

    @GetMapping("/{userId}/conversations")
    public List<ConversationSummaryDto> getConversations(@PathVariable("userId") Long userId) {
        Person user = personService.findPersonById(userId);
        return ConvesationMapper.toConversationSummaryDtoList(user.getConversations());
    }
}
