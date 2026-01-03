package com.example.chat.user.dto;

import com.example.chat.user.entity.Person;

public record AuthResponse(String token, Person person) {}
