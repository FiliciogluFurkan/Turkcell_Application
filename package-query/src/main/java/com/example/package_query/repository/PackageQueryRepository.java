package com.example.package_query.repository;

import com.example.package_query.entity.PackageEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PackageQueryRepository extends MongoRepository<PackageEntity,String> {
}
