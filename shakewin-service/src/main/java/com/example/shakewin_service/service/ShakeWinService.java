package com.example.shakewin_service.service;

import com.example.shakewin_service.dto.CreateShakeWin;
import com.example.shakewin_service.entity.ShakeWinEntity;
import com.example.shakewin_service.mapper.ShakeWinMapper;
import com.example.shakewin_service.repository.ShakeWinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ShakeWinService {
    private final ShakeWinRepository shakeWinRepository;
    private final ShakeWinMapper shakeWinMapper;

    public void addShakeWin(CreateShakeWin createShakeWin) {
        shakeWinRepository.save(shakeWinMapper.toEntity(createShakeWin));
    }

    public List<ShakeWinEntity> getAll() {
        return shakeWinRepository.findAll();
    }

    public ShakeWinEntity getById(Long id) {
        return shakeWinRepository.findById(id).orElse(null);
    }


    public void deleteShakeWin(Long id) {
        shakeWinRepository.deleteById(id);
    }

    public Boolean isShakeWinExist(Long id) {
        ShakeWinEntity byId = getById(id);
        if (byId != null) {
            return true;
        }
        return false;

    }

    public Long getShakeWinIdByRandomly() {

        List<ShakeWinEntity> allShakeWins = getAll();

        Random random = new Random();

        int n = random.nextInt(allShakeWins.size());

        return allShakeWins.get(n).getId();


    }
}
