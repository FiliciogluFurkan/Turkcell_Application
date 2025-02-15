package com.turkcell.user_command.application.dao;


import com.turkcell.user_command.application.dto.CreateUserRequestDto;
import com.turkcell.user_command.application.dto.UpdateUserRequestDto;
import com.turkcell.user_command.domain.User;

import java.util.Optional;

public interface UserDao {

    User saveUser(CreateUserRequestDto userRequestDto);
    User updateUser(Long id,UpdateUserRequestDto updateUserRequestDto);
    Optional<User> findUserById(Long id);
    Optional<User> findUserByMail(String mail);
    void deleteUser(Long id);
    void assignPackageToUser(Long userId, Long packageId);
    void assignExtraPackageToUser(Long userId, Long packageId);
    void makeShakeWin(Long userId,Long shakeWinId);

}

