package com.hexaware.cozyHeaven.hotelBooking.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.hexaware.cozyHeaven.hotelBooking.dto.AuthRequest;
import com.hexaware.cozyHeaven.hotelBooking.dto.BookingDTO;
import com.hexaware.cozyHeaven.hotelBooking.dto.HotelDTO;
import com.hexaware.cozyHeaven.hotelBooking.dto.PaymentDTO;
import com.hexaware.cozyHeaven.hotelBooking.dto.ReviewDTO;
import com.hexaware.cozyHeaven.hotelBooking.dto.RoomDTO;
import com.hexaware.cozyHeaven.hotelBooking.dto.UserDTO;
import com.hexaware.cozyHeaven.hotelBooking.dto.UserResponseDTO;
import com.hexaware.cozyHeaven.hotelBooking.entity.Room;

public interface IGuestService {

    List<HotelDTO> searchHotels(String location);

    //List<RoomDTO> findAvailableRooms(Long hotelId, LocalDate checkIn, LocalDate checkOut);

    double calculateTotalFare(RoomDTO room, int numAdults, int numChildren, List<Integer> childrenAges);

    BookingDTO bookRoom(BookingDTO bookingDTO);

    List<BookingDTO> viewBookingHistory(Long userId);

    BookingDTO cancelBooking(Long bookingId);
    
    UserResponseDTO getUserByFullNameAndPassword(String fullName, String password);
    

	List<RoomDTO> getRoomsByHotelID(Long hotelID);

	List<ReviewDTO> getReviewsByHotelID(Long hotelID);
	
	Set<String> getAllUniqueLocations();

	Optional<UserDTO> authenticate(AuthRequest request);

	// Optional<UserSummaryDTO> getUserSummaryByFullName(String fullName);

	Optional getUserSummaryByEmail(String email);

	ReviewDTO submitReview(ReviewDTO dto);

	PaymentDTO makePayment(PaymentDTO paymentDTO);

//	List<Room> getRoomsByAc(boolean ac);
//
//	List<Room> getRoomsByMaxOccupancy(int occupancy);
//
//	List<Room> getAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate);
}
