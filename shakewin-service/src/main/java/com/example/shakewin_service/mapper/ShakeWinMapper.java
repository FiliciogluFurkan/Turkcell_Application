package com.example.shakewin_service.mapper;

import com.example.shakewin_service.dto.CreateShakeWin;
import com.example.shakewin_service.entity.ShakeWinEntity;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Component
public class ShakeWinMapper {

    public ShakeWinEntity toEntity(CreateShakeWin createShakeWin){
        return ShakeWinEntity.builder()
                .description(createShakeWin.getDescription())
                .name(createShakeWin.getName())
                .type(createShakeWin.getType())
                .durationTime(createShakeWin.getDurationTime())
                .amount(createShakeWin.getAmount())
                .build();
    }

}
