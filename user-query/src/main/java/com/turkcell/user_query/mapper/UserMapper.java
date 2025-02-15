package com.turkcell.user_query.mapper;

import com.turkcell.user_query.dto.CreateUserRequestDto;
import com.turkcell.user_query.dto.UserResponseDto;
import com.turkcell.user_query.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class UserMapper {

    public UserResponseDto convertEntityToResponseDto(UserEntity userEntity) {
        return UserResponseDto.builder()
                .name(userEntity.getName())
                .surname(userEntity.getSurname())
                .mail(userEntity.getMail())
                .identityNumber(userEntity.getIdentityNumber())
                .age(userEntity.getAge())
                .id(userEntity.getId())
                .extraPackageIds(userEntity.getExtraPackageIds())
                .packageId(userEntity.getPackageId())
                .phoneNumber(userEntity.getPhoneNumber())
                .createdTime(userEntity.getCreatedTime())
                .updatedTime(userEntity.getUpdatedTime())
                .isShakeWinActive(userEntity.getIsShakeWinActive())
                .shakeWinId(userEntity.getShakeWinId())
                .build();
    }

    public UserEntity toModel(CreateUserRequestDto createUserRequestDto) {

        return UserEntity.builder()
                .name(createUserRequestDto.name())
                .surname(createUserRequestDto.surname())
                .phoneNumber(createUserRequestDto.phoneNumber())
                .mail(createUserRequestDto.identityNumber())
                .age(createUserRequestDto.age())
                .packageId(null)
                .identityNumber(createUserRequestDto.identityNumber())
                .extraPackageIds(null)
                .createdTime(ZonedDateTime.now())
                .isShakeWinActive(true)
                .shakeWinId(null)
                .updatedTime(null)
                .status(true).build();


    }


}
