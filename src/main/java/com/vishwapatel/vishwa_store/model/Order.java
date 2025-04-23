package com.vishwapatel.vishwa_store.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Date;

@Entity
@Table(name = "customer_order") // Renaming to avoid conflict with SQL keyword "Order"
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "Order date is required")
    private Date orderDate;

    @Min(value = 1, message = "Total amount must be greater than 0")
    private double totalAmount;

    @ManyToOne
    @JoinColumn(name = "customer_id") // Foreign Key to Customer
    @NotNull(message = "Customer ID is required")
    private Customer customer;

    // Constructors
    public Order() {}

    public Order(Date orderDate, double totalAmount, Customer customer) {
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.customer = customer;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Date getOrderDate() { return orderDate; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
}
