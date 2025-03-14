package com.example.package_command.adapters.out.postgresjpa.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class PackageEntity {


    @Id
    @SequenceGenerator(name = "package_id", allocationSize = 1)
    @GeneratedValue(generator = "package_id",strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "PacketName", nullable = false)
    private String packetName;

    @Column(name = "description", nullable = true)
    private String description;

    @PositiveOrZero(message = "price value should be zero or greater")
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @PositiveOrZero(message = "DurationDay value should be zero or greater")
    @Column(name = "durationTime", nullable = false)
    private Integer durationDay;

    @PositiveOrZero(message = "Minutes value should be zero or greater")
    @Column(name = "minutes", nullable = true)
    private Integer minutes;

    @PositiveOrZero(message = "Sms value should be zero or greater")
    @Column(name = "sms", nullable = true)
    private Integer sms;

    @PositiveOrZero(message = "Gb value should be zero or greater")
    @Column(name = "Gb", nullable = true)
    private Integer gb;

    @Column(name = "additional_features")
    private List<Long> extraFeatureIds;

    private ZonedDateTime createdTime;
    private ZonedDateTime updatedTime;
    private Boolean status;

}
