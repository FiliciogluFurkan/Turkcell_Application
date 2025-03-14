package com.example.extrapackage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PackageExistAndPrice {

    private Boolean isExist;
    private BigDecimal price;


}
