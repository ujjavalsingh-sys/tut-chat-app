package com.example.chat.user.dto;

public record LoginRequest(
        String username,
        String password
) {
}
