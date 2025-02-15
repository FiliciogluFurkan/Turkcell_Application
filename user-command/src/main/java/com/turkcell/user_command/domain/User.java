package com.turkcell.user_command.domain;

import java.time.ZonedDateTime;
import java.util.List;

public record User(
        Long id,
        String name,
        String surname,
        Integer age,
        String mail,
        String identityNumber,
        Boolean status,
        String phoneNumber,
        Long packageId,
        List<Long> extraPackageIds,
        ZonedDateTime createdTime,
        ZonedDateTime updatedTime,
        Boolean isShakeWinActive,
        Long shakeWinId

) {
}
