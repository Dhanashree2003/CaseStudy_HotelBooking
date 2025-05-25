package com.hexaware.cozyHeaven.hotelBooking.entity;


import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentID;

    @ManyToOne
    @JoinColumn(name = "BookingID")
    private Bookings booking; // Mapping to Booking entity

   
    private LocalDate paymentDate;

    private double amount;

    @Enumerated(EnumType.STRING)
    private PaymentDetails.PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private PaymentDetails.PaymentStatus paymentStatus;

    // Getters and Setters
}
