package com.example.package_command.application.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AssignExtraFeatureEvent {
    Long packageId;
    Long extraFeatureId;

}
