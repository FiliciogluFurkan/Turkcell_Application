package com.example.payment_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEntity {

    @Id
    @SequenceGenerator(name = "payment_id", allocationSize = 1)
    @GeneratedValue(generator = "payment_id", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private BigDecimal amount; // Ödeme miktarı

    @Column(nullable = false)
    private Long packageId;


    @Column(nullable = false)
    private ZonedDateTime paymentDate;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Column(nullable = true)
    private String transactionId;

    @Enumerated(EnumType.STRING)
    private PackageStatus packageStatus;

}
