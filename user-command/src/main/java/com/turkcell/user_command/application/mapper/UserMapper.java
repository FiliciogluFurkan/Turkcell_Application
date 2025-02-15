package com.turkcell.user_command.application.mapper;

import com.turkcell.user_command.adapters.out.postgresjpa.entity.UserEntity;
import com.turkcell.user_command.application.dto.CreateUserRequestDto;
import com.turkcell.user_command.application.dto.UserResponseDto;
import com.turkcell.user_command.domain.User;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class UserMapper {

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
                .updatedTime(null)
                .isShakeWinActive(true)
                .shakeWinId(null)
                .status(true).build();


    }

    public UserResponseDto toResponse(User user) {
        return UserResponseDto.builder()
                .name(user.name())
                .surname(user.surname())
                .age(user.age())
                .extraPackageIds(user.extraPackageIds())
                .mail(user.mail())
                .identityNumber(user.identityNumber())
                .createdTime(user.createdTime())
                .updatedTime(user.updatedTime())
                .packageId(user.packageId())
                .phoneNumber(user.phoneNumber())
                .isShakeWinActive(user.isShakeWinActive())
                .shakeWinId(user.shakeWinId())
                .build();
    }

    public User convertEntityToModel(UserEntity userEntiy) {
        User user = new User(userEntiy.getId(), userEntiy.getName(), userEntiy.getSurname(), userEntiy.getAge(), userEntiy.getMail(), userEntiy.getIdentityNumber(), userEntiy.getStatus(), userEntiy.getPhoneNumber(), userEntiy.getPackageId(), userEntiy.getExtraPackageIds(), userEntiy.getCreatedTime(), userEntiy.getUpdatedTime(), userEntiy.getIsShakeWinActive(), userEntiy.getShakeWinId());
        return user;
    }


}
