package com.example.package_command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageResponseDto {
    private Long id;
    private  String name;
    private String description;
    private  BigDecimal price;
    private Integer durationDay;
    private Integer minutes;
    private  Integer sms;
    private Integer gb;
    private List<Long> extraFeaturesId;
    private ZonedDateTime createdTime;
    private ZonedDateTime updatedTime;
 }
