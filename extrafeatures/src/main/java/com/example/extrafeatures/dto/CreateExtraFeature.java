package com.example.extrafeatures.dto;

import com.example.extrafeatures.entity.ExtraFeatureEntity;

public record CreateExtraFeature(

        ExtraFeatureEntity.FeatureType featureType,
        Integer durationTime
) {

}
