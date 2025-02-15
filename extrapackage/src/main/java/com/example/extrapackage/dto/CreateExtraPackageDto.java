package com.example.extrapackage.dto;

import java.math.BigDecimal;

public record CreateExtraPackageDto(

        String packetName,
        String description,
        BigDecimal price,
        Integer durationDay,
        Integer minutes,
        Integer sms,
        Integer gb
) {
}
