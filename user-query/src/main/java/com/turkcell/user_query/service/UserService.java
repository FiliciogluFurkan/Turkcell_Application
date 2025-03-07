package com.turkcell.user_query.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.turkcell.user_query.dto.*;
import com.turkcell.user_query.entity.UserEntity;
import com.turkcell.user_query.mapper.UserMapper;
import com.turkcell.user_query.repository.UserQueryRepository;
import com.turkcell.user_query.specification.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.turkcell.user_query.validation.ValidationMessages.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserQueryRepository userQueryRepository;
    private final UserMapper userMapper;
    private final ObjectMapper objectMapper;

    public ApiResponse<List<UserResponseDto>> getAllUser() {

        return ApiResponse.success(USERS_RETRIEVED_SUCCESFULLY, userQueryRepository.findAll().stream().filter(userEntity -> Objects.equals(Boolean.TRUE, userEntity.getStatus())).map(userEntity -> userMapper.convertEntityToResponseDto(userEntity)).collect(Collectors.toList()));

    }


    public ApiResponse<UserResponseDto> getUser(Long id) {
        return ApiResponse.success(USER_RETRIEVED_SUCCESFULLY, userMapper.convertEntityToResponseDto(userQueryRepository.findById(id).get()));
    }

    @KafkaListener(topics = "user-event", groupId = "user-event-group")
    public void processUserEvents(String userEventJson) throws IOException {
        try {
            UserEvent userEvent = objectMapper.readValue(userEventJson, UserEvent.class);
            log.info("user event is {}", userEvent);

            if (userEvent.getEventType().equals("CREATE_USER")) {
                userQueryRepository.save(userMapper.toModel(userEvent.getCreateUserRequestDto()));
            } else if (userEvent.getEventType().equals("UPDATE_USER")) {
                UserEntity userById = userQueryRepository.findById(userEvent.getId()).orElseThrow();
                UpdateUserRequestDto updateUserRequestDto = userEvent.getUpdateUserRequestDto();

                if (updateUserRequestDto.name() != null) {
                    userById.setName(updateUserRequestDto.name());
                }
                if (updateUserRequestDto.age() != null) {
                    userById.setAge(updateUserRequestDto.age());
                }
                if (updateUserRequestDto.mail() != null) {
                    userById.setMail(updateUserRequestDto.mail());
                }
                if (updateUserRequestDto.surname() != null) {
                    userById.setSurname(updateUserRequestDto.surname());
                }
                if (updateUserRequestDto.phoneNumber() != null) {
                    userById.setPhoneNumber(updateUserRequestDto.phoneNumber());
                }

                userQueryRepository.save(userById);
            } else if (userEvent.getEventType().equals("DELETE_USER")) {
                UserEntity userById = userQueryRepository.findById(userEvent.getId()).orElseThrow();
                userById.setStatus(false);
                userQueryRepository.save(userById);

            }

        } catch (Exception e) {
            log.error("Error processing user event: ", e);
        }
    }

    @KafkaListener(topics = "assign-package-to-user-event", groupId = "assign-package-event-id")
    public void assignPackageToUser(String assignPackageEventToUser) {
        try {

            AssignPackageEvent assignPackageEvent = objectMapper.readValue(assignPackageEventToUser, AssignPackageEvent.class);
            UserEntity userEntity = userQueryRepository.findById(assignPackageEvent.getUserId()).orElseThrow();
            if (assignPackageEvent.getType().equals("ASSIGN_PACKAGE")) {
                userEntity.setPackageId(assignPackageEvent.getPackageId());
                userQueryRepository.save(userEntity);
            } else if (assignPackageEvent.getType().equals("ASSIGN_EXTRA_PACKAGE")) {
                List<Long> extraPackageIds = userEntity.getExtraPackageIds();
                if (extraPackageIds == null) {
                    extraPackageIds = new ArrayList<>();
                }
                if (!extraPackageIds.contains(assignPackageEvent.getExtraPackageId())) {
                    extraPackageIds.add(assignPackageEvent.getExtraPackageId());
                    userEntity.setExtraPackageIds(extraPackageIds);
                    userQueryRepository.save(userEntity);
                }

            } else if (assignPackageEvent.getType().equals("MAKE_SHAKE_WIN")) {
                userEntity.setShakeWinId(assignPackageEvent.getShakeWinId());
                userEntity.setIsShakeWinActive(false);
                userQueryRepository.save(userEntity);
            }

        } catch (Exception e) {

        }

    }

    public ApiResponse<List<UserResponseDto>> search(String name, Integer age, Boolean status) {
        Specification<UserEntity> specification = Specification.where(UserSpecification.hasName(name)).and(UserSpecification.hasAgeGreaterThan(age)).and(UserSpecification.hasStatus(status));
        return ApiResponse.success("Users retrieved succesfully with the specialized search", userQueryRepository.findAll(specification).stream().map(userMapper::convertEntityToResponseDto).toList());
    }
}
