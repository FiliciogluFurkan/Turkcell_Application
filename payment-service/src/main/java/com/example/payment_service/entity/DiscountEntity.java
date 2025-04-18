package com.example.payment_service.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class DiscountEntity {

    @Id
    @SequenceGenerator(name = "discount_id", allocationSize = 1)
    @GeneratedValue(generator = "discount_id", strategy = GenerationType.SEQUENCE)
    private Long id;

    private Long packageId;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private PackageStatus packageStatus;

    private BigDecimal packagePrice;

    private BigDecimal amount;

    private BigDecimal newPrice;

    private String discountReason;

    private ZonedDateTime zonedDateTime;


}
