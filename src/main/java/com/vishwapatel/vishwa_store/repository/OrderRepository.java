package com.vishwapatel.vishwa_store.repository;

import com.vishwapatel.vishwa_store.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}