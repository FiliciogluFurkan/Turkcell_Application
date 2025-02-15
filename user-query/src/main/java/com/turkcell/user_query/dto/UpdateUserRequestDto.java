package com.turkcell.user_query.dto;

public record UpdateUserRequestDto(
        String name,
        String surname,
        Integer age,
        String mail,
        String phoneNumber
) {
}
