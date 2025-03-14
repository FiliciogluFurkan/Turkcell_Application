package com.example.payment_service.repository;

import com.example.payment_service.entity.UserBalance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBalanceRepository extends JpaRepository<UserBalance,Long> {

}
