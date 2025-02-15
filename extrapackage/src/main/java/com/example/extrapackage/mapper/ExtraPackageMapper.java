package com.example.extrapackage.mapper;

import com.example.extrapackage.dto.CreateExtraPackageDto;
import com.example.extrapackage.dto.ExtraPackageResponseDto;
import com.example.extrapackage.entity.ExtraPackageEntity;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class ExtraPackageMapper {

    public ExtraPackageEntity toEntity(CreateExtraPackageDto createExtraPackageDto) {

        return ExtraPackageEntity.builder()
                .gb(createExtraPackageDto.gb())
                .sms(createExtraPackageDto.sms())
                .minutes(createExtraPackageDto.minutes())
                .durationDay(createExtraPackageDto.durationDay())
                .description(createExtraPackageDto.description())
                .packetName(createExtraPackageDto.packetName())
                .price(createExtraPackageDto.price())
                .createdTime(ZonedDateTime.now())
                .updatedTime(ZonedDateTime.now())
                .status(true)
                .build();

    }
    public ExtraPackageResponseDto toResponse(ExtraPackageEntity extraPackageEntity){
        return ExtraPackageResponseDto.builder()

                .id(extraPackageEntity.getId())
                .sms(extraPackageEntity.getSms())
                .minutes(extraPackageEntity.getMinutes())
                .gb(extraPackageEntity.getGb())
                .createdTime(extraPackageEntity.getCreatedTime())
                .updatedTime(extraPackageEntity.getUpdatedTime())
                .description(extraPackageEntity.getDescription())
                .durationDay(extraPackageEntity.getDurationDay())
                .packetName(extraPackageEntity.getPacketName())
                .price(extraPackageEntity.getPrice())
                .build();
    }


}
