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

  @GetMapping("/{id}")
  public ConversationDto getConversationById(
      @PathVariable("id") Long id, @RequestParam("authUserId") Long authUserId) {
    return conversationService.getConversationDtoById(id, authUserId);
  }

  @GetMapping()
  public List<ConversationDto> getConversations(@RequestParam("participant") Long participantId) {
    return conversationService.getConversationDtosByUserId(participantId);
  }
}
