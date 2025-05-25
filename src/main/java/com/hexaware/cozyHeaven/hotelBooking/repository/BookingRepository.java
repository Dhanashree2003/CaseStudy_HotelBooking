package com.hexaware.cozyHeaven.hotelBooking.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.hexaware.cozyHeaven.hotelBooking.entity.Bookings;

public interface BookingRepository extends JpaRepository<Bookings, Long> {
}
