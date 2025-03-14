package com.example.payment_service.service;


import com.example.payment_service.entity.PackageStatus;
import com.example.payment_service.entity.PaymentEntity;
import com.example.payment_service.entity.PaymentStatus;
import com.example.payment_service.entity.UserBalance;
import com.example.payment_service.repository.PaymentRepository;
import com.example.payment_service.repository.UserBalanceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.stream.Stream;

import static com.example.payment_service.entity.PaymentStatus.FAILED;
import static com.example.payment_service.entity.PaymentStatus.SUCCESS;

@Service
@RequiredArgsConstructor
public class PaymentService {


    private final PaymentRepository paymentRepository;
    private final UserBalanceRepository userBalanceRepository;

    @Transactional
    public Boolean processPayment(String type, Long userId, Long packageId, BigDecimal price) {

        UserBalance userBalance = userBalanceRepository.findById(userId).orElse(null);

        if (userBalance != null) {
            PaymentEntity paymentEntity = new PaymentEntity();
            if (userBalance.getUserBalance().compareTo(price) < 0) {
                paymentEntity.setAmount(price);
                paymentEntity.setStatus(FAILED);
                paymentEntity.setPackageId(null);
                paymentEntity.setPackageStatus(null);
                paymentEntity.setPaymentDate(ZonedDateTime.now());
                paymentEntity.setUserId(userId);
                paymentRepository.save(paymentEntity);
                return false;
            } else {
                paymentEntity.setAmount(price);
                paymentEntity.setStatus(SUCCESS);
                paymentEntity.setPaymentDate(ZonedDateTime.now());
                paymentEntity.setUserId(userId);
                paymentEntity.setTransactionId(UUID.randomUUID().toString());
                paymentEntity.setPackageId(packageId);
                if (type.equals("PACKAGE")) {
                    paymentEntity.setPackageStatus(PackageStatus.MAIN_PACKAGE);
                } else if (type.equals("EXTRA_PACKAGE")) {
                    paymentEntity.setPackageStatus(PackageStatus.EXTRA_PACKAGE);
                }
                paymentRepository.save(paymentEntity);
                userBalance.setUserBalance(userBalance.getUserBalance().subtract(price));
                userBalanceRepository.save(userBalance);
                return true;
            }
        } else {
            return false;
        }
    }


}
