package com.example.package_command.adapters.out.postgresjpa;


import com.example.package_command.adapters.out.postgresjpa.entity.PackageEntity;
import com.example.package_command.adapters.out.postgresjpa.repository.PackageCommandRepository;
import com.example.package_command.application.dao.PackageDao;
import com.example.package_command.application.dto.CreatePackageRequestDto;
import com.example.package_command.application.dto.UpdatePackageRequestDto;
import com.example.package_command.application.mapper.PackageMapper;
import com.example.package_command.domain.Package;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PackageDaoAdapter implements PackageDao {

    private final PackageCommandRepository packageCommandRepository;
    private final PackageMapper packageMapper;

    @Override
    public Package savePackage(CreatePackageRequestDto createPackageRequestDto) {
        return packageMapper.convertEntityToDomain(packageCommandRepository.save(packageMapper.convertToEntiy(createPackageRequestDto)));
    }

    @Override
    public Package updatePackage(Long id, UpdatePackageRequestDto updateUserRequestDto) {

        PackageEntity byId = packageCommandRepository.findById(id).orElseThrow();
        if (updateUserRequestDto.name() != null) {
            byId.setPacketName(updateUserRequestDto.name());
        }
        if (updateUserRequestDto.description() != null) {
            byId.setDescription(updateUserRequestDto.description());
        }
        if (updateUserRequestDto.minutes() != null) {
            byId.setMinutes(updateUserRequestDto.minutes());
        }
        if (updateUserRequestDto.sms() != null) {
            byId.setSms(updateUserRequestDto.sms());
        }
        if (updateUserRequestDto.gb() != null) {
            byId.setGb(updateUserRequestDto.gb());
        }
        if (updateUserRequestDto.durationDay() != null) {
            byId.setDurationDay(updateUserRequestDto.durationDay());
        }
        if (updateUserRequestDto.price() != null) {
            byId.setPrice(updateUserRequestDto.price());
        }
        packageCommandRepository.save(byId);
        return packageMapper.convertEntityToDomain(byId);
    }

    @Override
    public void deletePackage(Long id) {
        PackageEntity packageEntity = packageCommandRepository.findById(id).get();
        packageEntity.setStatus(false);
        packageCommandRepository.save(packageEntity);


    }

    @Override
    public Optional<Package> findPackageById(Long id) {
        return packageCommandRepository.findById(id).map(packageEntity -> packageMapper.convertEntityToDomain(packageEntity));
    }

    @Override
    public Optional<Package> findPackageByName(String name) {
        return packageCommandRepository.findByPacketName(name).map(packageEntity -> packageMapper.convertEntityToDomain(packageEntity));
    }

    @Override
    public Package assignExtraFeature(Long packageId, Long extraFeatureId) {
        PackageEntity packageEntity = packageCommandRepository.findById(packageId)
                .orElseThrow(() -> new RuntimeException("Package not found with id: " + packageId));

        // Null kontrolü yaparak listeyi başlatıyoruz
        List<Long> extraFeatureIds = packageEntity.getExtraFeatureIds();
        if (extraFeatureIds == null) {
            extraFeatureIds = new ArrayList<>();
        }
        if(!extraFeatureIds.contains(extraFeatureId)) extraFeatureIds.add(extraFeatureId);

        packageEntity.setExtraFeatureIds(extraFeatureIds);

        return packageMapper.convertEntityToDomain(packageCommandRepository.save(packageEntity));
    }

}
