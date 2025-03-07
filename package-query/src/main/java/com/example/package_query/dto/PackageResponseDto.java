package com.example.package_query.dto;


import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PackageResponseDto implements Serializable {

    private Long id;
    private String packetName;
    private String description;
    private BigDecimal price;
    private Integer durationDay;
    private Integer minutes;
    private Integer sms;
    private Integer gb;
    private List<Long> extraFeatureIds;
    private Date createdTime;
    private Date updatedTime;

}
