package com.example.chat.chat.person;

import com.example.chat.chat.conversation.Conversation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class PersonController {
    private final PersonService personService;
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{userId}")
    public Person getPerson(@PathVariable("userId") Long userId) {
        return personService.findPersonById(userId);
    }

    @GetMapping("/{userId}/conversations")
    public Set<Conversation> getConversations(@PathVariable Long userId) {
        Person user = personService.findPersonById(userId);
        return user.getConversations();
    }
}
