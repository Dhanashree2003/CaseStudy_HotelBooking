package com.hexaware.cozyHeaven.hotelBooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hexaware.cozyHeaven.hotelBooking.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> findByLocation(String location);
}
