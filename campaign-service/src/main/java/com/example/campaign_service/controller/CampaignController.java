package com.example.campaign_service.controller;

import com.example.campaign_service.dto.UserPackageInfo;
import com.example.campaign_service.service.CampaignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/campaigns")
@RequiredArgsConstructor
public class CampaignController {


    private final CampaignService campaignService;

    @PostMapping("/apply-discount")
    public ResponseEntity<UserPackageInfo> applyDiscount(@RequestBody UserPackageInfo userPackageInfo) {
        UserPackageInfo updatedInfo = campaignService.applyDiscount(userPackageInfo);
        return ResponseEntity.ok(updatedInfo);
    }
}
