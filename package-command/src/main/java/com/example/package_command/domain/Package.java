package com.example.package_command.domain;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

public record Package(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Integer durationDay,
        Integer minutes,
        Integer sms,
        Integer gb,
        List<Long> extraFeaturesId,
        Boolean status,
        ZonedDateTime createdTime,
        ZonedDateTime updatedTime
) {
}
