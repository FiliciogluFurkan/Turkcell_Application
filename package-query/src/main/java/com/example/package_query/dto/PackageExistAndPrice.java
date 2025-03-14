package com.example.package_query.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PackageExistAndPrice {
    private Boolean isPackageExist;
    private BigDecimal price;
}
