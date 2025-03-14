package com.example.payment_service.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentProcessedEvent {

    private String type;
    private Long userId;
    private Long packageId;
    private Boolean paymentStatus;




}
