package com.example.package_command.application.dao;

import com.example.package_command.application.dto.CreatePackageRequestDto;
import com.example.package_command.application.dto.UpdatePackageRequestDto;
import com.example.package_command.domain.Package;

import java.util.Optional;

public interface PackageDao {

Package savePackage(CreatePackageRequestDto createPackageRequestDto);
Package updatePackage(Long id, UpdatePackageRequestDto updateUserRequestDto);
void deletePackage(Long id);
Optional<Package> findPackageById(Long id);
Optional<Package> findPackageByName(String name);
Package assignExtraFeature(Long packageId,Long extraFeatureId);


}
