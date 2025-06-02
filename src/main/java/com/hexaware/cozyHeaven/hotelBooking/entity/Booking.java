package com.hexaware.cozyHeaven.hotelBooking.entity;

import java.time.LocalDate;

import com.hexaware.cozyHeaven.hotelBooking.entity.enums.BookingStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "bookings")
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingID;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private User user; // Mapping to User entity

    @ManyToOne
    @JoinColumn(name = "RoomID")
    private Room room; // Mapping to Room entity

    
    private LocalDate checkInDate;

   
    private LocalDate checkOutDate;

    private int noOfAdults;

    private int noOfChildren;

    private double totalFare;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    
}
