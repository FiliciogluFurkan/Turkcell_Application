package com.example.extrafeatures.mapper;

import com.example.extrafeatures.dto.CreateExtraFeature;
import com.example.extrafeatures.dto.ExtraFeatureResponseDto;
import com.example.extrafeatures.entity.ExtraFeatureEntity;
import org.springframework.stereotype.Component;

@Component
public class ExtraFeatureMapper {

    public ExtraFeatureEntity toextraFeatureEntity(CreateExtraFeature createExtraFeature) {

        return ExtraFeatureEntity.builder()
                .featureType(createExtraFeature.featureType())
                .durationTime(createExtraFeature.durationTime())
                .build();
    }

    public ExtraFeatureResponseDto toResponseDto(ExtraFeatureEntity extraFeatureEntity) {
        return ExtraFeatureResponseDto.builder()
                .id(extraFeatureEntity.getId())
                .durationTime(extraFeatureEntity.getDurationTime())
                .featureType(extraFeatureEntity.getFeatureType())
                .build();
    }

}
