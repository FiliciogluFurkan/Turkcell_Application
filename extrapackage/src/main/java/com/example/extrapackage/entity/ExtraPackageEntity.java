package com.example.extrapackage.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
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
@Entity
public class ExtraPackageEntity {


    @Id
    @SequenceGenerator(name = "extrapackage_id", allocationSize = 1)
    @GeneratedValue(generator = "extrapackage_id",strategy = GenerationType.SEQUENCE)
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

    private ZonedDateTime createdTime;
    private ZonedDateTime updatedTime;
    private Boolean status;

    public void applyDiscount(BigDecimal discountPercentage) {
        this.price = this.price.subtract(this.price.multiply(discountPercentage).divide(BigDecimal.valueOf(100)));
    }

}
