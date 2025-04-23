package com.vishwapatel.vishwa_store.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Date;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "Payment date is required")
    private Date paymentDate;

    @Min(value = 1, message = "Amount must be greater than 0")
    private double amount;

    @OneToOne
    @JoinColumn(name = "order_id") // Foreign Key to Order
    private Order order;

    // Constructors
    public Payment() {}

    public Payment(Date paymentDate, double amount, Order order) {
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.order = order;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Date getPaymentDate() { return paymentDate; }
    public void setPaymentDate(Date paymentDate) { this.paymentDate = paymentDate; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }
}
