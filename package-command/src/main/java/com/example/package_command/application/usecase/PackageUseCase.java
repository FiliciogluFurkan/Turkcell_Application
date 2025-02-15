package com.example.package_command.application.usecase;

import com.example.package_command.adapters.in.client.ExtraFeatureClient;
import com.example.package_command.application.dao.PackageDao;
import com.example.package_command.application.dto.CreatePackageRequestDto;
import com.example.package_command.application.dto.PackageResponseDto;
import com.example.package_command.application.dto.UpdatePackageRequestDto;
import com.example.package_command.application.event.AssignExtraFeatureEvent;
import com.example.package_command.application.event.PackageEvent;
import com.example.package_command.application.mapper.PackageMapper;
import com.example.package_command.domain.Package;
import com.example.package_command.infrastructure.config.apiresponse.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import static com.example.package_command.infrastructure.config.validation.ValidationMessages.*;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PackageUseCase {

    private final PackageDao packageDao;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final ExtraFeatureClient extraFeatureClient;
    private final PackageMapper packageMapper;

    public ApiResponse<PackageResponseDto> savePackage(CreatePackageRequestDto createPackageRequestDto) throws JsonProcessingException {

        Optional<Package> packageByName = packageDao.findPackageByName(createPackageRequestDto.name());
        if (packageByName.isPresent()) {
            ApiResponse.failure(PACKAGE_ALREADY_EXIST);
        }

        Package savedPackage = packageDao.savePackage(createPackageRequestDto);
        PackageEvent packageEvent = new PackageEvent(savedPackage.id(), "CREATE_PACKAGE", createPackageRequestDto, null);
        kafkaTemplate.send("package-event", objectMapper.writeValueAsString(packageEvent));


        return ApiResponse.success(PACKAGE_ADDED_SUCCESSFULLY, packageMapper.toResponse(savedPackage));
    }

    public ApiResponse<PackageResponseDto> updatePackage(Long id, UpdatePackageRequestDto updatePackageRequestDto) throws JsonProcessingException {

        Optional<Package> packageById = packageDao.findPackageById(id);
        if (packageById.isPresent()) {
            Package updatedPackage = packageDao.updatePackage(id, updatePackageRequestDto);
            PackageEvent packageEvent = new PackageEvent(id, "UPDATE_PACKAGE", null, updatePackageRequestDto);
            kafkaTemplate.send("package-event", objectMapper.writeValueAsString(packageEvent));
           return ApiResponse.success(PACKAGE_UPDATED_SUCCESSFULLY,packageMapper.toResponse(updatedPackage));
        }

       return ApiResponse.failure(PACKAGE_NOT_FOUND);
    }

    public ApiResponse deletePackage(Long id) throws JsonProcessingException {

        Optional<Package> packageById = packageDao.findPackageById(id);
        if (!packageById.isPresent()) {
           ApiResponse.failure(PACKAGE_NOT_FOUND);
        }

        packageDao.deletePackage(id);

        PackageEvent packageEvent = new PackageEvent(id, "DELETE_PACKAGE", null, null);
        kafkaTemplate.send("package-event", objectMapper.writeValueAsString(packageEvent));

       return ApiResponse.failure(PACKAGE_DELETED_SUCCESSFULLY);


    }


    public ApiResponse assignExtraFeature(Long packageId, Long extraFeatureId) throws JsonProcessingException {

        packageDao.findPackageById(packageId).orElseThrow();
        ResponseEntity<Boolean> packageExist = extraFeatureClient.isPackageExist(extraFeatureId);
        if (packageExist.getBody()) {
            packageDao.assignExtraFeature(packageId, extraFeatureId);
            AssignExtraFeatureEvent extraFeatureEvent = new AssignExtraFeatureEvent(packageId, extraFeatureId);
            kafkaTemplate.send("assign-extra-feature-event", objectMapper.writeValueAsString(extraFeatureEvent));
            ApiResponse.success(EXTRA_FEATURE_ASSIGNED_SUCCESSFULLY);
        }

        return ApiResponse.failure(EXTRA_FEATURE_NOT_ASSIGNED);
    }
}
