package com.example.extrafeatures.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class ExtraFeatureEntity {

    @Id
    @SequenceGenerator(name = "extra_feature_id",allocationSize = 1)
    @GeneratedValue(generator = "extra_feature_id",strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(EnumType.STRING) // Enum'u String olarak sakla
    private FeatureType featureType;

    @PositiveOrZero
    @NotNull
    private Integer durationTime;


    public enum FeatureType {
        YOUTUBE_FAMILY,
        YOUTUBE_PERSONAL,
        SSPORT,
        ADDITIONAL_GB,

    }
}
