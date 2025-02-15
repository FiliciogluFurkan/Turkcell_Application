package com.turkcell.user_command.application.dto;


import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;
import java.util.List;

@SuperBuilder
@Data
public class UserResponseDto {

    private String name;
    private String surname;
    private Integer age;
    private String mail;
    private String identityNumber;
    private String phoneNumber;
    private Long packageId;
    private List<Long> extraPackageIds;
    private ZonedDateTime createdTime;
    private ZonedDateTime updatedTime;
    private Boolean isShakeWinActive;
    private Long shakeWinId;
}
