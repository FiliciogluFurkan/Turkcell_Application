package com.example.extrafeatures.service;


import com.example.extrafeatures.dto.ApiResponse;
import com.example.extrafeatures.dto.CreateExtraFeature;
import com.example.extrafeatures.dto.ExtraFeatureResponseDto;
import com.example.extrafeatures.entity.ExtraFeatureEntity;
import com.example.extrafeatures.mapper.ExtraFeatureMapper;
import com.example.extrafeatures.repository.ExtraFeatureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExtraFeatureService {

    private final ExtraFeatureRepository extraFeatureRepository;
    private final ExtraFeatureMapper mapper;

    public ApiResponse<ExtraFeatureResponseDto> saveExtraFeature(CreateExtraFeature createExtraFeature) {
        return ApiResponse.success("extraFeature added succesfully",mapper.toResponseDto(extraFeatureRepository.save(mapper.toextraFeatureEntity(createExtraFeature))));
    }

    public ApiResponse<List<ExtraFeatureResponseDto>> getAllExtraFeatures() {
        return ApiResponse.success("ExtraFeatures retrieved succesfully",extraFeatureRepository.findAll().stream().map(mapper::toResponseDto).collect(Collectors.toList()));
    }

    public ApiResponse<ExtraFeatureResponseDto> getById(Long id) {
        Optional<ExtraFeatureEntity> byId = extraFeatureRepository.findById(id);
        if (byId.isPresent()) {
            return ApiResponse.success("extraFeature founded succesfully", mapper.toResponseDto(byId.get()));
        }
        return ApiResponse.failure("extraFeature not found");

    }

    public void deleteExtraFeature(Long id) {
        extraFeatureRepository.deleteById(id);
    }


    public Boolean isExist(Long id) {
        ApiResponse<ExtraFeatureResponseDto> byId = getById(id);
        if(byId.isSuccess()){
            return true;
        }

        return false;
    }
}
