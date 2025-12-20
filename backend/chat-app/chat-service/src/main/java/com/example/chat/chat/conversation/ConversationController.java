package com.example.chat.chat.conversation;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conversations")
public class ConversationController {
    private final ConversationService conversationService;
    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @PostMapping
    public Conversation createConversation() {
        return conversationService.createConversation();
    }

    @GetMapping("/{id}")
    public ConversationDto getConversationById(@PathVariable("id") Long id) {
        return ConvesationMapper.toConversationDto(conversationService.getConversationById(id));
    }
}
