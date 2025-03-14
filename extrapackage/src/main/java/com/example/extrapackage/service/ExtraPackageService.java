package com.example.extrapackage.service;


import com.example.extrapackage.dto.ApiResponse;
import com.example.extrapackage.dto.CreateExtraPackageDto;
import com.example.extrapackage.dto.ExtraPackageResponseDto;
import com.example.extrapackage.dto.PackageExistAndPrice;
import com.example.extrapackage.entity.ExtraPackageEntity;
import com.example.extrapackage.mapper.ExtraPackageMapper;
import com.example.extrapackage.repository.ExtraPackageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExtraPackageService {

    private final ExtraPackageRepository extraPackageRepository;
    private final ExtraPackageMapper extraPackageMapper;

    public ApiResponse<ExtraPackageResponseDto> saveExtraPackage(CreateExtraPackageDto createExtraPackageDto) {
        Optional<ExtraPackageEntity> byPacketName = extraPackageRepository.findByPacketName(createExtraPackageDto.packetName());

        if (byPacketName.isPresent()) {
            return ApiResponse.failure("Package already exists");
        }

        ExtraPackageEntity savedEntity = extraPackageRepository.save(extraPackageMapper.toEntity(createExtraPackageDto));
        return ApiResponse.success("Extra Package added successfully", extraPackageMapper.toResponse(savedEntity));
    }

    public ApiResponse<List<ExtraPackageResponseDto>> getAllExtraPackage() {

        return ApiResponse.success("ExtraPackages retrieved succesfully", extraPackageRepository.findAll().
                stream().
                filter(extraPackageEntity -> Objects.equals(Boolean.TRUE, extraPackageEntity.getStatus())).
                map(extraPackageMapper::toResponse).
                collect(Collectors.toList()));
    }

    public ApiResponse<ExtraPackageResponseDto> getExtraPackage(Long id) {
        return extraPackageRepository.findById(id)
                .map(extraPackageMapper::toResponse)
                .map(extraPackageResponseDto -> ApiResponse.success("ExtraPackage retrieved successfully", extraPackageResponseDto))
                .orElseGet(() -> ApiResponse.failure("ExtraPackage not found"));
    }


    public void deleteExtraPackage(Long id) {
        ExtraPackageEntity extraPackageEntity = extraPackageRepository.findById(id).orElseThrow();
        extraPackageEntity.setStatus(false);
        extraPackageRepository.save(extraPackageEntity);

    }

    public PackageExistAndPrice isExtraPackageExist(Long id) {

        ApiResponse<ExtraPackageResponseDto> extraPackage = getExtraPackage(id);
        PackageExistAndPrice packageExistAndPrice = new PackageExistAndPrice();
        if(extraPackage.getData().getId()!=null){
            packageExistAndPrice.setIsExist(Boolean.TRUE);
        }
        else{
            packageExistAndPrice.setIsExist(Boolean.FALSE);
        }
        packageExistAndPrice.setPrice(extraPackage.getData().getPrice());

        return packageExistAndPrice;


    }
}
