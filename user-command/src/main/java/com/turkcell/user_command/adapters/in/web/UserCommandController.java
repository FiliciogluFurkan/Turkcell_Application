package com.turkcell.user_command.adapters.in.web;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.turkcell.user_command.application.dto.CreateUserRequestDto;
import com.turkcell.user_command.application.dto.UpdateUserRequestDto;
import com.turkcell.user_command.application.dto.UserResponseDto;
import com.turkcell.user_command.application.usecases.UserUseCase;
import com.turkcell.user_command.domain.User;
import com.turkcell.user_command.infrastructure.apiresponse.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserCommandController {

    private UserUseCase userUseCase;

    public UserCommandController(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserResponseDto>> saveUser(@Valid @RequestBody CreateUserRequestDto createUserRequestDto) throws JsonProcessingException {
        return ResponseEntity.ok(userUseCase.saveUser(createUserRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) throws JsonProcessingException {
        return ResponseEntity.ok(userUseCase.deleteUser(id));
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody UpdateUserRequestDto updateUserRequestDto) throws JsonProcessingException {
        return userUseCase.updateUser(id, updateUserRequestDto);
    }

    @PatchMapping("/{userid}/{packageid}")
    public ResponseEntity<ApiResponse> assignPackageToUser(@PathVariable("userid") Long userId, @PathVariable("packageid") Long packageId) throws JsonProcessingException {
        return ResponseEntity.ok(userUseCase.assignPackageToUser(userId, packageId));
    }

    @PatchMapping("/assignExtraPackage/{userid}/{packageid}")
    public ResponseEntity<ApiResponse> assignExtraPackageToUser(@PathVariable("userid") Long userId, @PathVariable("packageid") Long packageId) throws JsonProcessingException {
        return ResponseEntity.ok(userUseCase.assignExtraPackageToUser(userId,packageId));
    }

    @PatchMapping("/makeshakewin/{userid}")
    public ResponseEntity<ApiResponse> makeShakeWin(@PathVariable("userid") Long userId) throws JsonProcessingException {
        return ResponseEntity.ok(userUseCase.makeShakeWin(userId));
    }

}
