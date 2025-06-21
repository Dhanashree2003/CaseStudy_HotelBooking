package com.hexaware.cozyHeaven.hotelBooking.dto;

import java.time.LocalDate;

import com.hexaware.cozyHeaven.hotelBooking.entity.enums.PaymentMethod;
import com.hexaware.cozyHeaven.hotelBooking.entity.enums.PaymentStatus;

import lombok.Data;

@Data
public class PaymentDTO {

	private Long paymentID;
    private Long bookingID;
    private LocalDate paymentDate;
    private double amount;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private String transactionID;
    private String mobileNumber;
    private String bankName;
	
}
