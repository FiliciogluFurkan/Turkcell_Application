package com.turkcell.user_command.application.dto;




public record CreateUserRequestDto(
        String name,
        String surname,
        String identityNumber,
        Integer age,
        String mail,
        String phoneNumber
) {}
