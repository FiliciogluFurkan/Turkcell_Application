package com.turkcell.user_command.application.dto;

public record UpdateUserRequestDto(
        String name,
        String surname,
        Integer age,
        String mail,
        String phoneNumber
) {
}
