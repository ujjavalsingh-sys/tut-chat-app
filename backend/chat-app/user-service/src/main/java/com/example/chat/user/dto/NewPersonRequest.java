package com.example.chat.user.dto;

public record NewPersonRequest(
        String username,
        String password
) {
}
