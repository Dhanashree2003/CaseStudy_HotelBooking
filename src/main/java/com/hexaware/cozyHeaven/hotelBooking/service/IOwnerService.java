package com.hexaware.cozyHeaven.hotelBooking.service;

import java.util.List;

import com.hexaware.cozyHeaven.hotelBooking.dto.BookingDTO;
import com.hexaware.cozyHeaven.hotelBooking.dto.HotelDTO;
import com.hexaware.cozyHeaven.hotelBooking.dto.PaymentDTO;
import com.hexaware.cozyHeaven.hotelBooking.dto.ReviewDTO;
import com.hexaware.cozyHeaven.hotelBooking.dto.RoomDTO;
import com.hexaware.cozyHeaven.hotelBooking.dto.UserDTO;
import com.hexaware.cozyHeaven.hotelBooking.entity.Room;

public interface IOwnerService {
	
	
	// Booking management
    List<BookingDTO> getAllBookings(Long ownerId);

    BookingDTO updateBookingStatus(Long bookingId, BookingDTO bookingDTO);

    // Room management
    List<RoomDTO> getAllRooms(Long hotelID);


    //RoomDTO updateRoom(Long roomId, RoomDTO roomDTO);

    void deleteRoom(Long roomId);

    // Payment viewing
    List<PaymentDTO> getAllPayments(Long ownerId);

    // Hotel viewing
    List<HotelDTO> getHotelsByOwner(Long ownerId);

    // Review viewing
    List<ReviewDTO> getReviewsByHotel(Long hotelId);

    // User listing (optional)
    List<UserDTO> getAllUsers();

	Room saveRoom(Room room);

	RoomDTO updateRoom(Long roomId, RoomDTO updatedRoomDTO);

}
