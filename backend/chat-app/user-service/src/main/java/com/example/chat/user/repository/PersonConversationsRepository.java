package com.example.chat.user.repository;

import com.example.chat.user.entity.PersonConversations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonConversationsRepository extends JpaRepository<PersonConversations, Long> {
}
