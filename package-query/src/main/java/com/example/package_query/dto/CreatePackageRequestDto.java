package com.example.package_query.dto;

import java.math.BigDecimal;

public record CreatePackageRequestDto(
        String name,
        String description,
        BigDecimal price,
        Integer durationDay,
        Integer minutes,
        Integer sms,
        Integer gb) {
}
