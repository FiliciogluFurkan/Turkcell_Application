package com.example.package_command.application.event;

import com.example.package_command.application.dto.CreatePackageRequestDto;
import com.example.package_command.application.dto.UpdatePackageRequestDto;
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
