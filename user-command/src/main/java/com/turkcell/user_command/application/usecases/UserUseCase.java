package com.turkcell.user_command.application.usecases;

import brave.internal.extra.Extra;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.turkcell.user_command.adapters.in.client.ExtraPackageClient;
import com.turkcell.user_command.adapters.in.client.PackageClient;
import com.turkcell.user_command.adapters.in.client.ShakeWinClient;
import com.turkcell.user_command.application.dao.UserDao;
import com.turkcell.user_command.application.dto.CreateUserRequestDto;
import com.turkcell.user_command.application.dto.UpdateUserRequestDto;
import com.turkcell.user_command.application.dto.UserResponseDto;
import com.turkcell.user_command.application.event.AssignPackageEvent;
import com.turkcell.user_command.application.event.UserEvent;
import com.turkcell.user_command.application.mapper.UserMapper;
import com.turkcell.user_command.domain.User;
import com.turkcell.user_command.infrastructure.apiresponse.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.turkcell.user_command.infrastructure.validationmessages.ValidationMessages.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserUseCase {

    private final UserDao userDao;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final UserMapper userMapper;
    private final PackageClient packageClient;
    private final ExtraPackageClient extraPackageClient;
    private final ShakeWinClient shakeWinClient;


    public ApiResponse<UserResponseDto> saveUser(CreateUserRequestDto userRequestDto) throws JsonProcessingException {

        User isExistUser = userDao.findUserByMail(userRequestDto.mail()).orElse(null);

        if (isExistUser != null) {
            return ApiResponse.failure(USER_NOT_FOUND);
        }


        UserEvent userEvent = new UserEvent(null, "CREATE_USER", userRequestDto, null);
        kafkaTemplate.send("user-event", objectMapper.writeValueAsString(userEvent));


        return ApiResponse.success(USER_ADDED_SUCCESFULLY, userMapper.toResponse(userDao.saveUser(userRequestDto)));
    }


    public User updateUser(Long id, UpdateUserRequestDto updateUserRequestDto) throws JsonProcessingException {

        Optional<User> userEntityOptional = userDao.findUserById(id);


        if (userEntityOptional.isPresent()) {
            return userDao.updateUser(id, updateUserRequestDto);
        }

        UserEvent userEvent = new UserEvent(id, "UPDATE_USER", null, updateUserRequestDto);
        kafkaTemplate.send("user-event", objectMapper.writeValueAsString(userEvent));
        return null;
    }

    public ApiResponse<Void> deleteUser(Long id) throws JsonProcessingException {
        User isExistUser = userDao.findUserById(id).orElseGet(null);

        if (isExistUser == null) {
            ApiResponse.failure(USER_NOT_FOUND);
        }
        userDao.deleteUser(id);
        UserEvent userEvent = new UserEvent(id, "DELETE_USER", null, null);
        kafkaTemplate.send("user-event", objectMapper.writeValueAsString(userEvent));
        return ApiResponse.success(USER_DELETED_SUCCESFULLY);
    }


    public ApiResponse assignPackageToUser(Long userId, Long packageId) throws JsonProcessingException {

        userDao.findUserById(userId).orElseThrow();
        Boolean packageExists = packageClient.isPackageExist(packageId).getBody();

        if (!packageExists) {
            return ApiResponse.failure(PACKAGE_ASSIGMENT_FAILED);
        }

        userDao.assignPackageToUser(userId, packageId);

        AssignPackageEvent assignPackageEvent = new AssignPackageEvent("ASSIGN_PACKAGE", userId, packageId, null,null);
        kafkaTemplate.send("assign-package-to-user-event", objectMapper.writeValueAsString(assignPackageEvent));

        return ApiResponse.success(PACKAGE_ASSIGNED_SUCCESFULLY);
    }

    public ApiResponse assignExtraPackageToUser(Long userId, Long packageId) throws JsonProcessingException {

        userDao.findUserById(userId).orElseThrow();
        ResponseEntity<Boolean> isExtraPackageExist = extraPackageClient.isExtraPackageExist(packageId);

        if (!isExtraPackageExist.getBody()) {
            return ApiResponse.failure(PACKAGE_ASSIGMENT_FAILED);
        }

        userDao.assignExtraPackageToUser(userId, packageId);

        AssignPackageEvent assignPackageEvent = new AssignPackageEvent("ASSIGN_EXTRA_PACKAGE", userId, null, packageId,null);
        kafkaTemplate.send("assign-package-to-user-event", objectMapper.writeValueAsString(assignPackageEvent));

        return ApiResponse.success(PACKAGE_ASSIGNED_SUCCESFULLY);


    }

    public ApiResponse makeShakeWin(Long userId) throws JsonProcessingException {

        User user = userDao.findUserById(userId).orElseThrow();

        if (!user.isShakeWinActive()) {
            return ApiResponse.failure(SHAKE_WIN_FAILURE);
        }

        Long packageId = shakeWinClient.getShakeWinRandomly().getBody();
        userDao.makeShakeWin(userId, packageId);

        AssignPackageEvent assignPackageEvent=new AssignPackageEvent("MAKE_SHAKE_WIN",userId,null,null,packageId);
        kafkaTemplate.send("assign-package-to-user-event", objectMapper.writeValueAsString(assignPackageEvent));

        return ApiResponse.success(PACKAGE_ASSIGNED_SUCCESFULLY);


    }
}
