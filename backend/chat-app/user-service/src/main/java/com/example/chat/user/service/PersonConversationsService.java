package com.example.chat.user.service;

import com.example.chat.user.repository.PersonConversationsRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonConversationsService {
    private final PersonConversationsRepository personConversationsRepository;
    public PersonConversationsService(PersonConversationsRepository personConversationsRepository) {
        this.personConversationsRepository = personConversationsRepository;
    }
}
