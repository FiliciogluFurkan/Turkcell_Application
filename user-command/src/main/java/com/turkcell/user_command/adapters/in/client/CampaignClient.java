package com.turkcell.user_command.adapters.in.client;

import com.turkcell.user_command.application.dto.UserPackageInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange
public interface CampaignClient {

    @PostExchange("/campaigns/apply-discount")
    public ResponseEntity<UserPackageInfo> applyDiscount(@RequestBody UserPackageInfo userPackageInfo);

}
