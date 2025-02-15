package com.example.package_query.mapper;

import com.example.package_query.dto.CreatePackageRequestDto;
import com.example.package_query.dto.PackageResponseDto;
import com.example.package_query.entity.PackageEntity;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class PackageMapper {

    public PackageResponseDto convertEntityToResponse(PackageEntity packageEntity) {
        return PackageResponseDto.builder()
                .gb(packageEntity.getGb())
                .minutes(packageEntity.getMinutes())
                .sms(packageEntity.getSms())
                .createdTime(packageEntity.getCreatedTime())
                .updatedTime(packageEntity.getUpdatedTime())
                .description(packageEntity.getDescription())
                .durationDay(packageEntity.getDurationDay())
                .packetName(packageEntity.getPacketName())
                .extraFeatureIds(packageEntity.getExtraFeatureIds())
                .price(packageEntity.getPrice())
                .id(Long.valueOf(packageEntity.getId()))
                .build();
    }

    public PackageEntity toEntity(Long id, CreatePackageRequestDto packageRequestDto) {
        return PackageEntity.builder()
                .id(id.toString())
                .packetName(packageRequestDto.name())
                .description(packageRequestDto.description())
                .sms(packageRequestDto.sms())
                .gb(packageRequestDto.gb())
                .minutes(packageRequestDto.minutes())
                .durationDay(packageRequestDto.durationDay())
                .createdTime(new Date())
                .updatedTime(null)
                .status(true)
                .extraFeatureIds(null)
                .price(packageRequestDto.price())
                .build();

    }


}
