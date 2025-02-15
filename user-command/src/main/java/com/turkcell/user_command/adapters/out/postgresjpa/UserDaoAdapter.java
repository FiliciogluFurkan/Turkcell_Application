package com.turkcell.user_command.adapters.out.postgresjpa;

import com.turkcell.user_command.adapters.out.postgresjpa.entity.UserEntity;
import com.turkcell.user_command.adapters.out.postgresjpa.repository.UserCommandRepository;
import com.turkcell.user_command.application.dao.UserDao;
import com.turkcell.user_command.application.dto.CreateUserRequestDto;
import com.turkcell.user_command.application.dto.UpdateUserRequestDto;
import com.turkcell.user_command.application.mapper.UserMapper;
import com.turkcell.user_command.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserDaoAdapter implements UserDao {

    private UserCommandRepository userCommandRepository;
    private UserMapper userMapper;

    public UserDaoAdapter(UserCommandRepository userCommandRepository, UserMapper userMapper) {
        this.userCommandRepository = userCommandRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User saveUser(CreateUserRequestDto userRequestDto) {
        return userMapper.convertEntityToModel(userCommandRepository.save(userMapper.toModel(userRequestDto)));
    }

    @Override
    public User updateUser(Long id, UpdateUserRequestDto updateUserRequestDto) {
        UserEntity userEntity = userCommandRepository.findById(id).orElseGet(null);
        if (updateUserRequestDto.phoneNumber() != null) {
            userEntity.setPhoneNumber(updateUserRequestDto.phoneNumber());
        }
        if (updateUserRequestDto.age() != null) {
            userEntity.setPhoneNumber(updateUserRequestDto.phoneNumber());
        }
        if (updateUserRequestDto.name() != null) {
            userEntity.setName(updateUserRequestDto.name());
        }
        if (updateUserRequestDto.surname() != null) {
            userEntity.setSurname(updateUserRequestDto.surname());
        }
        if (updateUserRequestDto.mail() != null) {
            userEntity.setMail(updateUserRequestDto.mail());
        }
        return userMapper.convertEntityToModel(userCommandRepository.save(userEntity));

    }


    @Override
    public Optional<User> findUserById(Long id) {
        // findById Optional<UserEntity> döndürüyor, bu yüzden Optional'ı mapliyoruz
        return userCommandRepository.findById(id)
                .map(userEntity -> userMapper.convertEntityToModel(userEntity));
    }


    @Override
    public Optional<User> findUserByMail(String mail) {
        return userCommandRepository.findByMail(mail).map(userEntity -> userMapper.convertEntityToModel(userEntity));
    }

    @Override
    public void deleteUser(Long id) {

        UserEntity userEntity = userCommandRepository.findById(id).orElseThrow();
        userEntity.setStatus(false);
        userCommandRepository.save(userEntity);

    }

    @Transactional
    public void assignPackageToUser(Long userId, Long packageId) {
        userCommandRepository.updateUserPackage(userId, packageId);
    }

    @Override
    public void assignExtraPackageToUser(Long userId, Long packageId) {
        Optional<UserEntity> byId = userCommandRepository.findById(userId);
        List<Long> extraPackageIds = byId.get().getExtraPackageIds();
        if (extraPackageIds == null) {
            extraPackageIds = new ArrayList<>();
        }
        if (!extraPackageIds.contains(packageId))
            extraPackageIds.add(packageId);

        UserEntity userEntity = byId.get();
        userEntity.setExtraPackageIds(extraPackageIds);

        userCommandRepository.save(userEntity);

    }

    @Override
    public void makeShakeWin(Long userId, Long shakeWinId) {

        UserEntity byId = userCommandRepository.findById(userId).map(userEntity -> {
            userEntity.setIsShakeWinActive(false);
            userEntity.setShakeWinId(shakeWinId);
            return userEntity;

        }).orElseThrow();

        userCommandRepository.save(byId);
    }

}
