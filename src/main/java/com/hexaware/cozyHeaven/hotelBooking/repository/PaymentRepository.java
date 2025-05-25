package com.hexaware.cozyHeaven.hotelBooking.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.hexaware.cozyHeaven.hotelBooking.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
