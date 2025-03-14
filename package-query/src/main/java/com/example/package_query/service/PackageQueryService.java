package com.example.package_query.service;


import com.example.package_query.dto.*;
import com.example.package_query.entity.PackageEntity;
import com.example.package_query.mapper.PackageMapper;
import com.example.package_query.repository.PackageQueryRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PackageQueryService {

    private final PackageQueryRepository packageQueryRepository;
    private final PackageMapper packageMapper;
    private final ObjectMapper objectMapper;
    private final RedisTemplate<String, Object> redisTemplate;
    private static final String HASH_KEY = "Package";

    public ApiResponse<List<PackageResponseDto>> getAllPackages() {
        // Cache kontrolü
        List<Object> cachedValues = redisTemplate.opsForHash().values(HASH_KEY);
        if (!cachedValues.isEmpty()) {
            List<PackageResponseDto> responseDtos = cachedValues.stream()
                    .map(o -> objectMapper.convertValue(o, PackageResponseDto.class)) // OKUMA İÇİN UYUMLU
                    .toList();
            return ApiResponse.success("Packages retrieved from cache", responseDtos);
        }

        // Eğer Cache Boşsa DB'den Al ve Cache'e Kaydet
        List<PackageEntity> packageEntities = packageQueryRepository.findAll();
        List<PackageResponseDto> packageResponseDtos = packageEntities.stream()
                .map(packageMapper::convertEntityToResponse)
                .collect(Collectors.toList());

        // Cache'e Kaydet
        for (PackageResponseDto packageResponseDto : packageResponseDtos) {
            redisTemplate.opsForHash().put(HASH_KEY, String.valueOf(packageResponseDto.getId()), packageResponseDto);
        }

        return ApiResponse.success("Packages have been retrieved from database", packageResponseDtos);
    }


    public ApiResponse<PackageResponseDto> getPackage(Long id) {
        return packageQueryRepository.findById(String.valueOf(id))
                .map(packageEntity -> ApiResponse.success("Package has been retrieved", packageMapper.convertEntityToResponse(packageEntity)))
                .orElseGet(() -> ApiResponse.failure("Package not found"));
    }


    @KafkaListener(topics = "package-event", groupId = "package-group-id")
    public void processPackageEvent(String packageEventJson) throws JsonProcessingException {

        try {
            PackageEvent packageEvent = objectMapper.readValue(packageEventJson, PackageEvent.class);
            if (packageEvent.getEventType().equals("CREATE_PACKAGE")) {
                packageQueryRepository.save(packageMapper.toEntity(packageEvent.getId(), packageEvent.getCreatePackageRequestDto()));
                redisTemplate.opsForHash().put(HASH_KEY, String.valueOf(packageEvent.getId()), packageMapper.toEntity(packageEvent.getId(), packageEvent.getCreatePackageRequestDto()));
            } else if (packageEvent.getEventType().equals("UPDATE_PACKAGE")) {
                PackageEntity packageEntity = packageQueryRepository.findById(String.valueOf(packageEvent.getId())).orElseThrow();
                UpdatePackageRequestDto updatePackageRequestDto = packageEvent.getUpdatePackageRequestDto();
                if (updatePackageRequestDto.name() != null) {
                    packageEntity.setPacketName(updatePackageRequestDto.name());
                }
                if (updatePackageRequestDto.description() != null) {
                    packageEntity.setDescription(updatePackageRequestDto.description());
                }
                if (updatePackageRequestDto.gb() != null) {
                    packageEntity.setGb(updatePackageRequestDto.gb());
                }
                if (updatePackageRequestDto.sms() != null) {
                    packageEntity.setSms(updatePackageRequestDto.sms());
                }
                if (updatePackageRequestDto.minutes() != null) {
                    packageEntity.setMinutes(updatePackageRequestDto.minutes());
                }
                if (updatePackageRequestDto.durationDay() != null) {
                    packageEntity.setDurationDay(updatePackageRequestDto.durationDay());
                }
                if (updatePackageRequestDto.price() != null) {
                    packageEntity.setPrice(updatePackageRequestDto.price());
                }
                packageQueryRepository.save(packageEntity);

            } else if (packageEvent.getEventType().equals("DELETE_PACKAGE")) {
                PackageEntity packageEntity = packageQueryRepository.findById(String.valueOf(packageEvent.getId())).orElseThrow();
                packageEntity.setStatus(false);
                packageQueryRepository.save(packageEntity);
            }


        } catch (Exception e) {

        }

    }

    @KafkaListener(topics = "assign-extra-feature-event", groupId = "assign-extra-feature-id")
    public void AssignExtraFeatureToPackage(String assignExtraFeatureEvent) throws JsonProcessingException {
        AssignExtraFeatureEvent event = objectMapper.readValue(assignExtraFeatureEvent, AssignExtraFeatureEvent.class);

        Optional<PackageEntity> byId = packageQueryRepository.findById(String.valueOf(event.getPackageId()));
        if (byId.isPresent()) {
            PackageEntity packageEntity = byId.get();

            List<Long> extraFeatureIds = packageEntity.getExtraFeatureIds();
            if (extraFeatureIds == null) {
                extraFeatureIds = new ArrayList<>();
            }

            if (!extraFeatureIds.contains(event.getExtraFeatureId()))
                extraFeatureIds.add(event.getExtraFeatureId());

            packageEntity.setExtraFeatureIds(extraFeatureIds);
            packageQueryRepository.save(packageEntity);
        } else {
            // Package bulunamazsa yapılacak işlem
            System.out.println("Package not found with ID: " + event.getPackageId());
        }
    }


    public PackageExistAndPrice isPackageExist(Long packageId) {

        ApiResponse<PackageResponseDto> responsedPackage = getPackage(packageId);
        PackageExistAndPrice packageExistAndPrice = new PackageExistAndPrice();
        if (responsedPackage.isSuccess()) {
            packageExistAndPrice.setPrice(responsedPackage.getData().getPrice());
            packageExistAndPrice.setIsPackageExist(true);
            return packageExistAndPrice;
        }

        packageExistAndPrice.setIsPackageExist(false);
        packageExistAndPrice.setPrice(null);
        return packageExistAndPrice;

    }


}
