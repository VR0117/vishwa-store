package com.vishwapatel.vishwa_store.controller;

import com.vishwapatel.vishwa_store.model.Payment;
import com.vishwapatel.vishwa_store.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public List<com.vishwapatel.vishwa_store.model.Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{id}")
    public Optional<com.vishwapatel.vishwa_store.model.Payment> getPaymentById(@PathVariable Long id) {
        return paymentService.getPaymentById(id);
    }

    @PostMapping
    public com.vishwapatel.vishwa_store.model.Payment createPayment(@RequestBody Payment payment) {
        return paymentService.savePayment((com.vishwapatel.vishwa_store.model.Payment) payment);
    }

    @DeleteMapping("/{id}")
    public void deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
    }
}
