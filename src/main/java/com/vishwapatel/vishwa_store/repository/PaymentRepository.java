package com.vishwapatel.vishwa_store.repository;

import com.vishwapatel.vishwa_store.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}