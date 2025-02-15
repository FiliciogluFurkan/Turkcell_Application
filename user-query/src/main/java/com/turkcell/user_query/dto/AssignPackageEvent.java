package com.turkcell.user_query.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignPackageEvent {

    private String type;
    private Long userId;
    private Long packageId;
    private Long extraPackageId;
    private Long shakeWinId;


}
