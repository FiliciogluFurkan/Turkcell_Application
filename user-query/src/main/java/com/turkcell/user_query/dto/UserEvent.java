package com.turkcell.user_query.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEvent {

    private Long id;
    private String eventType;
    private CreateUserRequestDto createUserRequestDto;
    private UpdateUserRequestDto updateUserRequestDto;
}
