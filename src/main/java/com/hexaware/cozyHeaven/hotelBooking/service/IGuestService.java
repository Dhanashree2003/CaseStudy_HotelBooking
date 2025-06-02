package com.hexaware.cozyHeaven.hotelBooking.service;

import java.time.LocalDate;
import java.util.List;

import com.hexaware.cozyHeaven.hotelBooking.dto.BookingDTO;
import com.hexaware.cozyHeaven.hotelBooking.dto.HotelDTO;
import com.hexaware.cozyHeaven.hotelBooking.dto.RoomDTO;

public interface IGuestService {

    List<HotelDTO> searchHotels(String location);

    // List<RoomDTO> findAvailableRooms(Long hotelId, LocalDate checkIn, LocalDate checkOut);

    double calculateTotalFare(RoomDTO room, int numAdults, int numChildren, List<Integer> childrenAges);

    BookingDTO bookRoom(BookingDTO bookingDTO);

    List<BookingDTO> viewBookingHistory(Long userId);

    BookingDTO cancelBooking(Long bookingId);
}
