package com.example.campaign_service.service;

import com.example.campaign_service.dto.UserPackageInfo;
import lombok.RequiredArgsConstructor;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CampaignService {

    private final KieContainer kieContainer;

    public UserPackageInfo applyDiscount(UserPackageInfo userPackageInfo) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(userPackageInfo);
        kieSession.fireAllRules();
        kieSession.dispose();
        return userPackageInfo;
    }
    
}
