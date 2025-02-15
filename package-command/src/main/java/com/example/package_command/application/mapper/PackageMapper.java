package com.example.package_command.application.mapper;

import com.example.package_command.adapters.out.postgresjpa.entity.PackageEntity;
import com.example.package_command.application.dto.CreatePackageRequestDto;
import com.example.package_command.application.dto.PackageResponseDto;
import com.example.package_command.domain.Package;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class PackageMapper {


    public PackageEntity convertToEntiy(CreatePackageRequestDto packageRequestDto) {

        return PackageEntity.builder()
                .packetName(packageRequestDto.name())
                .description(packageRequestDto.description())
                .sms(packageRequestDto.sms())
                .gb(packageRequestDto.gb())
                .minutes(packageRequestDto.minutes())
                .durationDay(packageRequestDto.durationDay())
                .createdTime(ZonedDateTime.now())
                .updatedTime(null)
                .status(true)
                .extraFeatureIds(null)
                .price(packageRequestDto.price())
                .build();

    }

    public PackageResponseDto toResponse(Package convertedPackage) {
        return PackageResponseDto.builder()
                .id(convertedPackage.id())
                .name(convertedPackage.name())
                .description(convertedPackage.description())
                .durationDay(convertedPackage.durationDay())
                .gb(convertedPackage.gb())
                .sms(convertedPackage.sms())
                .minutes(convertedPackage.minutes())
                .createdTime(convertedPackage.createdTime())
                .updatedTime(convertedPackage.updatedTime())
                .price(convertedPackage.price())
                .extraFeaturesId(convertedPackage.extraFeaturesId())
                .build();
    }

    public Package convertEntityToDomain(PackageEntity packageEntity) {

        Package packageDomain = new Package(packageEntity.getId(), packageEntity.getPacketName(), packageEntity.getDescription(), packageEntity.getPrice(), packageEntity.getDurationDay(), packageEntity.getMinutes(), packageEntity.getSms(), packageEntity.getGb(), packageEntity.getExtraFeatureIds(), packageEntity.getStatus(), packageEntity.getCreatedTime(), packageEntity.getUpdatedTime());
        return packageDomain;

    }


}
