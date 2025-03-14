package com.example.payment_service.consumer;


import com.example.payment_service.entity.UserBalance;
import com.example.payment_service.repository.UserBalanceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserConsumer {

    private final UserBalanceRepository userBalanceRepository;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "user-event", id = "user_event_id")
    public void saveUserToBalanceDatabase(String userEvent) {

        UserBalance userBalance = new UserBalance();
        userBalanceRepository.save(userBalance);

    }


}
