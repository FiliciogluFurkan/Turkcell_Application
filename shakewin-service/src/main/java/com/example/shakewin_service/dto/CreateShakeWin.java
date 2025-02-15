package com.example.shakewin_service.dto;

import com.example.shakewin_service.entity.ShakeWinEntity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateShakeWin {

    private String name;
    private String description;
    private Integer amount;
    private Integer durationTime;
    private ShakeWinEntity.Type type;

}
