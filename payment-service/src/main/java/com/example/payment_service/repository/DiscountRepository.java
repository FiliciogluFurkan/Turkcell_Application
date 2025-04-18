package com.example.payment_service.repository;

import com.example.payment_service.entity.DiscountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<DiscountEntity,Long> {
}
