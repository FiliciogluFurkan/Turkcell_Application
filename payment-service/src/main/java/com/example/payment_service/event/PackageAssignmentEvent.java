package com.example.payment_service.event;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PackageAssignmentEvent {
    private String type;
    private Long userId;
    private Long packageId;
    private Long extraPackageId;
    private Long shakeWinId;
    private BigDecimal price;

}
