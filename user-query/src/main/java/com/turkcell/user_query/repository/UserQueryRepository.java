package com.turkcell.user_query.repository;

import com.turkcell.user_query.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserQueryRepository extends JpaRepository<UserEntity,Long> , JpaSpecificationExecutor<UserEntity> {
}
