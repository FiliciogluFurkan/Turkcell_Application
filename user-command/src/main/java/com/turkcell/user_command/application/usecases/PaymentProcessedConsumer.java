package com.turkcell.user_command.application.usecases;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.turkcell.user_command.application.event.PaymentProcessedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentProcessedConsumer {


    private final UserUseCase userUseCase;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "payment-processed", groupId = "payment_processed_id")
    public void isPaymentSuccesfull(String event) throws JsonProcessingException {

        PaymentProcessedEvent paymentProcessedEvent = objectMapper.readValue(event, PaymentProcessedEvent.class);
        if (paymentProcessedEvent.getPaymentStatus()) {
           if(paymentProcessedEvent.getType().equals("PACKAGE")){
               userUseCase.finalizePackageAssigment(paymentProcessedEvent.getUserId(), paymentProcessedEvent.getPackageId());
           }
           else{
               userUseCase.finalizeExtraPackageAssignment(paymentProcessedEvent.getUserId(), paymentProcessedEvent.getPackageId());
           }
        } else {
            log.error("Payment Failed");
        }

    }


}
