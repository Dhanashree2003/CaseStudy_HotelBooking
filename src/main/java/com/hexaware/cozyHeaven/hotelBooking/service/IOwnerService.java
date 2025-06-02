package com.hexaware.cozyHeaven.hotelBooking.service;

import java.util.List;

import com.hexaware.cozyHeaven.hotelBooking.dto.BookingDTO;
import com.hexaware.cozyHeaven.hotelBooking.dto.HotelDTO;
import com.hexaware.cozyHeaven.hotelBooking.dto.PaymentDTO;
import com.hexaware.cozyHeaven.hotelBooking.dto.ReviewDTO;
import com.hexaware.cozyHeaven.hotelBooking.dto.RoomDTO;
import com.hexaware.cozyHeaven.hotelBooking.dto.UserDTO;

public interface IOwnerService {
	
	
	// Booking management
    List<BookingDTO> getAllBookings(Long ownerId);

    BookingDTO updateBookingStatus(Long bookingId, BookingDTO bookingDTO);

    // Room management
    List<RoomDTO> getAllRooms(Long ownerId);

    RoomDTO addRoom(RoomDTO roomDTO);

    //RoomDTO updateRoom(Long roomId, RoomDTO roomDTO);

    void deleteRoom(Long roomId);

    // Payment viewing
    List<PaymentDTO> getAllPayments(Long ownerId);

    // Hotel viewing
    List<HotelDTO> getHotelsByOwner(Long ownerId);

    // Review viewing
    List<ReviewDTO> getReviewsByOwner(Long ownerId);

    // User listing (optional)
    List<UserDTO> getAllUsers();

}
