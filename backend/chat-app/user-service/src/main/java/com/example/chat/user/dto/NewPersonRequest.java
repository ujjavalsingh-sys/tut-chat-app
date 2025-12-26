package com.example.chat.user.dto;

public record NewPersonRequest(
        String firstname,
        String lastname,
        String username,
        String password
) {
}
