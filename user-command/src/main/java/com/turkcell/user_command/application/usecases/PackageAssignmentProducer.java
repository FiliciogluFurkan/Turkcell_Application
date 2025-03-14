package com.turkcell.user_command.application.usecases;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.turkcell.user_command.application.event.AssignPackageEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PackageAssignmentProducer {


    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void sendPackageAssignmentEvent(Long userId, Long packageId, BigDecimal price) throws JsonProcessingException {
        AssignPackageEvent event = new AssignPackageEvent("PACKAGE", userId, packageId, null, null, price);
        kafkaTemplate.send("make-payment", objectMapper.writeValueAsString(event));
    }

    public void sendExtraPackageAssignmentEvent(Long userId, Long packageId, BigDecimal price) throws JsonProcessingException {
        AssignPackageEvent event = new AssignPackageEvent("EXTRA_PACKAGE", userId, null, packageId, null, price);
        kafkaTemplate.send("make-payment", objectMapper.writeValueAsString(event));
    }


}
