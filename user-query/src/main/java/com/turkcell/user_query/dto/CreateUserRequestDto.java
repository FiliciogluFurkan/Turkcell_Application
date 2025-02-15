package com.turkcell.user_query.dto;


public record CreateUserRequestDto(
        String name,
        String surname,
        String identityNumber,
        Integer age,
        String mail,
        String phoneNumber,
        Boolean isShakeWinActive,
        Long shakeWinId
) {}
