package com.turkcell.user_query.repository;

import com.turkcell.user_query.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserQueryRepository extends JpaRepository<UserEntity,Long> {
}
