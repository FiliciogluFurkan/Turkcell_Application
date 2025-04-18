package com.example.payment_service.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PackageExistAndPrice {
    private Boolean isExist;
    private BigDecimal price;

}
