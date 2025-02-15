package com.example.package_command.adapters.in.web;

import com.example.package_command.application.dto.CreatePackageRequestDto;
import com.example.package_command.application.dto.PackageResponseDto;
import com.example.package_command.application.dto.UpdatePackageRequestDto;
import com.example.package_command.application.usecase.PackageUseCase;
import com.example.package_command.infrastructure.config.apiresponse.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/package")
public class PackageCommandController {

    private final PackageUseCase packageUseCase;

    @PostMapping
    public ResponseEntity<ApiResponse<PackageResponseDto>> addPackage(@Valid @RequestBody CreatePackageRequestDto createPackageRequestDto) throws JsonProcessingException {
        return ResponseEntity.ok(packageUseCase.savePackage(createPackageRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deletePackage(@PathVariable Long id) throws JsonProcessingException {
        return ResponseEntity.ok(packageUseCase.deletePackage(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PackageResponseDto>> updatePackage(@PathVariable Long id, @RequestBody UpdatePackageRequestDto updatePackageRequestDto) throws JsonProcessingException {
        return ResponseEntity.ok(packageUseCase.updatePackage(id, updatePackageRequestDto));
    }

    @PatchMapping("/{packageid}/{extrafeatureid}")
    public ResponseEntity<ApiResponse> assignExtraFeaatureToPackage(@PathVariable("packageid") Long packageId, @PathVariable("extrafeatureid") Long extraFeatureId) throws JsonProcessingException {
        return ResponseEntity.ok(packageUseCase.assignExtraFeature(packageId, extraFeatureId));
    }


}
