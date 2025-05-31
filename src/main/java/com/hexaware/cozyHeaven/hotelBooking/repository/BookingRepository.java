package com.hexaware.cozyHeaven.hotelBooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hexaware.cozyHeaven.hotelBooking.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	@Query("SELECT b FROM Booking b WHERE b.user.userID = :userId")
	List<Booking> findBookingsByUserId(@Param("userId") Long userId);
	
	List<Booking> findByRoom_Hotel_Owner_UserID(Long ownerId);
    // This list bookings for rooms where the room's hotel's owner matches ownerId

}
