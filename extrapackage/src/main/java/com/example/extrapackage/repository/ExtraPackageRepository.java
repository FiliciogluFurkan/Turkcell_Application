package com.example.extrapackage.repository;

import com.example.extrapackage.entity.ExtraPackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExtraPackageRepository extends JpaRepository<ExtraPackageEntity,Long> {

    Optional<ExtraPackageEntity> findByPacketName(String name);

}
