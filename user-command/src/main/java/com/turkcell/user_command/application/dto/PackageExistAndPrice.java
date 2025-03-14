package com.turkcell.user_command.application.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class PackageExistAndPrice {

    @JsonProperty("isExist") // Gelen JSON'daki "isExist" verisini "isPackageExist" ile eşleştir
    private Boolean isPackageExist;
    private BigDecimal price;

}
