package com.example.chat.chat.message;

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
    public MessageDto createMessage(@RequestBody CreateMessageRequest request) {
        return MessageMapper.toDto(messageService.sendMessage(request.conversationId(),request.senderId(), request.text()));
    }

    @GetMapping("/{conversationId}")
    public List<MessageDto> getAllMessages(@PathVariable Long conversationId) {
        return MessageMapper.toDtoList(messageService.getMessages(conversationId));
    }
}
