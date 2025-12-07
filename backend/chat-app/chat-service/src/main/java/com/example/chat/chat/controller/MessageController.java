package com.example.chat.chat.controller;

import com.example.chat.chat.entity.Message;
import com.example.chat.chat.service.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    private final MessageService messageService;
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public Message createMessage(
            @RequestParam Long conversationId,
            @RequestParam Long senderId,
            @RequestParam String text
    ) {
        return messageService.sendMessage(conversationId, senderId, text);
    }

    @GetMapping("/{conversationId}")
    public List<Message> getAllMessages(@PathVariable Long conversationId) {
        return messageService.getMessages(conversationId);
    }
}
