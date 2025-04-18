package com.turkcell.user_command.application.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserPackageInfo {
    private Long userId;
    private Long mainPackageId;
    private Long extraPackageId;
    private BigDecimal extraPackagePrice;
    private String discountReason;
}
