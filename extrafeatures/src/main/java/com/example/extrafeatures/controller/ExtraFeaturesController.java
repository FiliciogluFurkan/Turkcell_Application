package com.example.extrafeatures.controller;

import com.example.extrafeatures.dto.ApiResponse;
import com.example.extrafeatures.dto.CreateExtraFeature;
import com.example.extrafeatures.dto.ExtraFeatureResponseDto;
import com.example.extrafeatures.service.ExtraFeatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/extrafeatures")
public class ExtraFeaturesController {
    private final ExtraFeatureService extraFeatureService;

    @PostMapping
    public ResponseEntity<ApiResponse<ExtraFeatureResponseDto>> saveExtraFeature(@RequestBody CreateExtraFeature createExtraFeature) {
        return ResponseEntity.ok(extraFeatureService.saveExtraFeature(createExtraFeature));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ExtraFeatureResponseDto>>> getAllExtraFeatures() {
        return ResponseEntity.ok(extraFeatureService.getAllExtraFeatures());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ExtraFeatureResponseDto>> getExtraFeatures(@PathVariable Long id) {
        return ResponseEntity.ok(extraFeatureService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteExtraFeature(@PathVariable Long id) {
        extraFeatureService.deleteExtraFeature(id);
    }

    @GetMapping("/ispackageexist/{id}")
    public ResponseEntity<Boolean> isPackageExist(@PathVariable Long id){
        return ResponseEntity.ok(extraFeatureService.isExist(id));

    }


}
