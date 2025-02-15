package com.example.package_query.entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Document(collection = "package_query")
public class PackageEntity {

    @Id
    private String id;

    @Field("packetName")
    private String packetName;

    @Field("description")
    private String description;

    @PositiveOrZero(message = "price value should be zero or greater")
    @Field("price")
    private BigDecimal price;

    @PositiveOrZero(message = "DurationDay value should be zero or greater")
    @Field("durationTime")
    private Integer durationDay;

    @PositiveOrZero(message = "Minutes value should be zero or greater")
    @Field("minutes")
    private Integer minutes;

    @PositiveOrZero(message = "Sms value should be zero or greater")
    @Field("sms")
    private Integer sms;

    @PositiveOrZero(message = "Gb value should be zero or greater")
    @Field("Gb")
    private Integer gb;

    @Field("additional_features")
    private List<Long> extraFeatureIds;

    @Field("createdTime")
    private Date createdTime;

    @Field("updatedTime")
    private Date updatedTime;

    @Field("status")
    private Boolean status;
}
