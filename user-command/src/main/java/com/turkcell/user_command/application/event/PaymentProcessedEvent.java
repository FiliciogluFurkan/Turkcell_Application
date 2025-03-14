package com.turkcell.user_command.application.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentProcessedEvent {

    private String type;
    private Long userId;
    private Long packageId;
    private Boolean paymentStatus;


}
