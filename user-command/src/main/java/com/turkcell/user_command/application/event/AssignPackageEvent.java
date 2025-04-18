package com.turkcell.user_command.application.event;


import com.turkcell.user_command.application.dto.UserPackageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignPackageEvent {

    private String type;
    private Long userId;
    private Long packageId;
    private Long extraPackageId;
    private Long shakeWinId;
    private BigDecimal price;
    private String discountReason;
}
