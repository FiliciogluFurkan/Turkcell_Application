package com.example.package_command.application.dto;

import java.math.BigDecimal;


public record CreatePackageRequestDto(
        String name,
        String description,
        BigDecimal price,
        Integer durationDay,
        Integer minutes,
        Integer sms,
        Integer gb)
{

}
