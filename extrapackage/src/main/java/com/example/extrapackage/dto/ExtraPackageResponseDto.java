package com.example.extrapackage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ExtraPackageResponseDto {

    private Long id;
    private String packetName;
    private String description;
    private BigDecimal price;
    private Integer durationDay;
    private Integer minutes;
    private Integer sms;
    private Integer gb;
    private ZonedDateTime createdTime;
    private ZonedDateTime updatedTime;

}
