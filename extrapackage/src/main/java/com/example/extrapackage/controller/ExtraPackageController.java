package com.example.extrapackage.controller;

import com.example.extrapackage.dto.ApiResponse;
import com.example.extrapackage.dto.CreateExtraPackageDto;
import com.example.extrapackage.dto.ExtraPackageResponseDto;
import com.example.extrapackage.dto.PackageExistAndPrice;
import com.example.extrapackage.service.ExtraPackageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/extrapackage")
public class ExtraPackageController {

    private final ExtraPackageService extraPackageService;

    @PostMapping
    public ResponseEntity<ApiResponse<ExtraPackageResponseDto>> addExtraPackage(@Valid @RequestBody CreateExtraPackageDto createExtraPackageDto){

        return ResponseEntity.ok(extraPackageService.saveExtraPackage(createExtraPackageDto));

    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<ExtraPackageResponseDto>>> getAllPackages(){

        return ResponseEntity.ok(extraPackageService.getAllExtraPackage());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ExtraPackageResponseDto>> getPackage(@PathVariable Long id){

        return ResponseEntity.ok(extraPackageService.getExtraPackage(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){

        extraPackageService.deleteExtraPackage(id);
    }

    @GetMapping("isextrapackageexist/{extrapackageid}")
    public ResponseEntity<PackageExistAndPrice> isExtraPackageExist(@PathVariable("extrapackageid") Long id){
        return ResponseEntity.ok(extraPackageService.isExtraPackageExist(id));
    }


}
