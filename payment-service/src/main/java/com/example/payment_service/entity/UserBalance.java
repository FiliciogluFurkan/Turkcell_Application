package com.example.payment_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserBalance {

    @Id
    @SequenceGenerator(name = "user_balance_seq",allocationSize = 1)
    @GeneratedValue(generator = "user_balance_seq",strategy = GenerationType.SEQUENCE)
    private Long userId;

    private BigDecimal userBalance;

}
