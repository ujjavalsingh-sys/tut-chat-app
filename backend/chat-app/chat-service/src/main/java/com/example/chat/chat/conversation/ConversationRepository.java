package com.example.chat.chat.conversation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    List<Conversation> findByParticipants_Id(Long id);
}
