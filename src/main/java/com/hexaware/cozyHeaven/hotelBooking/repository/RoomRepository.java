package com.hexaware.cozyHeaven.hotelBooking.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hexaware.cozyHeaven.hotelBooking.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

	@Query("SELECT r FROM Room r WHERE r.hotel.id = :hotelId AND r.id NOT IN (SELECT b.room.id FROM Booking b WHERE b.checkInDate <= :endDate AND b.checkOutDate >= :startDate)")
	List<Room> findAvailableRoomsByHotelIdAndDate(@Param("hotelId") Long hotelId,
	                                               @Param("startDate") LocalDate startDate,
	                                               @Param("endDate") LocalDate endDate);
	
	List<Room> findByHotel_Owner_UserID(Long ownerId);

}
