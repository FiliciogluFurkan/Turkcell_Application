package com.example.package_query.controller;


import com.example.package_query.dto.ApiResponse;
import com.example.package_query.dto.PackageResponseDto;
import com.example.package_query.service.PackageQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/package")
public class PackageQueryController {

    private final PackageQueryService packageQueryService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<PackageResponseDto>>> getAllPackages() {
        return ResponseEntity.ok(packageQueryService.getAllPackages());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PackageResponseDto>> getPackage(@PathVariable Long id) {
        return ResponseEntity.ok(packageQueryService.getPackage(id));
    }

    @GetMapping("ispackageexist/{id}")
    public ResponseEntity<Boolean> isPackageExist(@PathVariable("id") Long packageId) {
    return ResponseEntity.ok(packageQueryService.isPackageExist(packageId));
    }



}

