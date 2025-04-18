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

        PackageAssignmentEvent event = objectMapper.readValue(paymentEvent, PackageAssignmentEvent.class);

        String cardHolderName = "Ahmet Yılmaz";
        String cardNumber = "5890040000000016";
        String expireMonth = "12";
        String expireYear = "2025";
        String cvc = "152";

        Boolean isPaymentSuccessfull = paymentService.processPayment(
                event.getType(),
                event.getUserId(),
                event.getExtraPackageId(),
                event.getPrice(),
                event.getDiscountReason(),
                cardHolderName,
                cardNumber,
                expireMonth,
                expireYear,
                cvc
        );

        PaymentProcessedEvent processedEvent = new PaymentProcessedEvent(
                event.getType(),
                event.getUserId(),
                event.getType().equals("PACKAGE")? event.getPackageId() : event.getExtraPackageId(),
                isPaymentSuccessfull
        );

        kafkaTemplate.send("payment-processed", objectMapper.writeValueAsString(processedEvent));
    }
}
