package com.example.extrafeatures.dto;

import com.example.extrafeatures.entity.ExtraFeatureEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtraFeatureResponseDto {
    private Long id;
    private ExtraFeatureEntity.FeatureType featureType;
    private Integer durationTime;
}
