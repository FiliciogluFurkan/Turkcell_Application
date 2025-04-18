package com.example.payment_service.service;


import com.example.payment_service.client.ExtraPackageClient;
import com.example.payment_service.dto.PackageExistAndPrice;
import com.example.payment_service.entity.*;
import com.example.payment_service.repository.DiscountRepository;
import com.example.payment_service.repository.PaymentRepository;
import com.example.payment_service.repository.UserBalanceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    private final DiscountRepository discountRepository;
    private final ExtraPackageClient extraPackageClient;
    private final IyzicoPaymentService iyzicoService; // Yeni eklenen servis

    @Transactional
    public Boolean processPayment(String type,
                                  Long userId,
                                  Long packageId,
                                  BigDecimal price,
                                  String discountReason,
                                  String cardHolderName,
                                  String cardNumber,
                                  String expireMonth,
                                  String expireYear,
                                  String cvc) {

        try {
            // 1. Iyzico ile ödeme yap
            boolean paymentSuccess = iyzicoService.makePayment(
                    price,
                    cardHolderName,
                    cardNumber,
                    expireMonth,
                    expireYear,
                    cvc,
                    userId.toString()
            );

            // 2. Ödeme sonucuna göre işlemler
            if(paymentSuccess) {
                createSuccessEntities(type, userId, packageId, price, discountReason);
                return true;
            } else {
                createFailedPayment(userId,packageId,type, price);
                return false;
            }

        } catch (Exception e) {
            createFailedPayment(userId, packageId,type,price);
            return false;
        }
    }

    private void createSuccessEntities(String type,
                                       Long userId,
                                       Long packageId,
                                       BigDecimal price,
                                       String discountReason) {
        // PaymentEntity oluştur
        PaymentEntity payment = new PaymentEntity();
        payment.setAmount(price);

        payment.setStatus(SUCCESS);
        payment.setPaymentDate(ZonedDateTime.now());
        payment.setUserId(userId);
        payment.setTransactionId(UUID.randomUUID().toString());
        payment.setPackageId(packageId);
        payment.setPackageStatus(type.equals("PACKAGE") ?
                PackageStatus.MAIN_PACKAGE : PackageStatus.EXTRA_PACKAGE);

        paymentRepository.save(payment);

        // Extra package ise discount oluştur
        if(type.equals("EXTRA_PACKAGE")) {
            DiscountEntity discount = new DiscountEntity();
            discount.setPackageId(packageId);
            discount.setDiscountReason(discountReason);
            discount.setUserId(userId);
            discount.setZonedDateTime(ZonedDateTime.now());
            discount.setPackageStatus(PackageStatus.EXTRA_PACKAGE);

            ResponseEntity<PackageExistAndPrice> response = extraPackageClient.isExtraPackageExist(packageId);
            if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                BigDecimal originalPrice = response.getBody().getPrice();
                discount.setAmount(originalPrice.subtract(price));
                discount.setPackagePrice(originalPrice);
                discount.setNewPrice(price);
            }

            discountRepository.save(discount);
        }
    }

    private void createFailedPayment(Long userId, Long packageId,String packageStatus,BigDecimal price) {
        PaymentEntity payment = new PaymentEntity();
        payment.setAmount(price);
        payment.setPackageStatus(packageStatus.equals("PACKAGE") ? PackageStatus.MAIN_PACKAGE : PackageStatus.EXTRA_PACKAGE );
        payment.setPackageId(packageId);
        payment.setStatus(FAILED);
        payment.setPaymentDate(ZonedDateTime.now());
        payment.setUserId(userId);
        payment.setTransactionId(UUID.randomUUID().toString());
        paymentRepository.save(payment);
    }
}