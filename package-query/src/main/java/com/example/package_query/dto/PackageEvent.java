package com.example.package_query.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageEvent {

    private Long id;
    private String eventType;
    private CreatePackageRequestDto createPackageRequestDto;
    private UpdatePackageRequestDto updatePackageRequestDto;

}
