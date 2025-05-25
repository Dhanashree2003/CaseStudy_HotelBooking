package com.hexaware.cozyHeaven.hotelBooking.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.hexaware.cozyHeaven.hotelBooking.entity.Rooms;

public interface RoomRepository extends JpaRepository<Rooms, Long> {
}
