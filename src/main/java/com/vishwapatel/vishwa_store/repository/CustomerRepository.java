package com.vishwapatel.vishwa_store.repository;


import com.vishwapatel.vishwa_store.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}

