package com.turkcell.user_command.adapters.out.postgresjpa.repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.turkcell.user_command.adapters.out.postgresjpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCommandRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByMail(String mail);

    @Modifying
    @Query("UPDATE UserEntity u SET u.packageId = :packageId WHERE u.id = :userId")
    void updateUserPackage(@Param("userId") Long userId, @Param("packageId") Long packageId);
}
