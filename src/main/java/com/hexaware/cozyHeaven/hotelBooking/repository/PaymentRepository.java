package com.hexaware.cozyHeaven.hotelBooking.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.cozyHeaven.hotelBooking.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
	
	List<Payment> findByBooking_Room_Hotel_Owner_UserID(Long ownerId);
	
	boolean existsByTransactionID(String transactionID);
    //boolean existsByMobileNumber(String mobileNumber);
}
