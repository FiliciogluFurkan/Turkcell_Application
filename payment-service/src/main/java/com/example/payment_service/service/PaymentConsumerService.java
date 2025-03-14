package com.example.payment_service.service;

import com.example.payment_service.event.PackageAssignmentEvent;
import com.example.payment_service.event.PaymentProcessedEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentConsumerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final PaymentService paymentService;


    @KafkaListener(topics = "make-payment", groupId = "payment")
    public void listenPackageAssigment(String paymentEvent) throws JsonProcessingException {

        PackageAssignmentEvent packageAssignmentEvent = objectMapper.readValue(paymentEvent, PackageAssignmentEvent.class);
        Boolean isPaymentSuccessfull = paymentService.processPayment(packageAssignmentEvent.getType(),packageAssignmentEvent.getUserId(),packageAssignmentEvent.getExtraPackageId(), packageAssignmentEvent.getPrice());

        PaymentProcessedEvent paymentProcessedEvent = new PaymentProcessedEvent(
                packageAssignmentEvent.getType(),
                packageAssignmentEvent.getUserId(),
                packageAssignmentEvent.getPackageId(),
                isPaymentSuccessfull
        );

        kafkaTemplate.send("payment-processed", objectMapper.writeValueAsString(paymentProcessedEvent));


    }


}
