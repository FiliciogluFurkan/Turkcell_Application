package com.example.package_command.adapters.out.postgresjpa.repository;

import com.example.package_command.adapters.out.postgresjpa.entity.PackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PackageCommandRepository extends JpaRepository<PackageEntity,Long> {

    Optional<PackageEntity> findByPacketName(String name);

}
