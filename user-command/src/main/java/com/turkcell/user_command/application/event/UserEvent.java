package com.turkcell.user_command.application.event;

import com.turkcell.user_command.application.dto.CreateUserRequestDto;
import com.turkcell.user_command.application.dto.UpdateUserRequestDto;
import jakarta.validation.constraints.NotNull;
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
