package com.hexaware.cozyHeaven.hotelBooking.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.cozyHeaven.hotelBooking.dto.BookingDTO;
import com.hexaware.cozyHeaven.hotelBooking.dto.HotelDTO;
import com.hexaware.cozyHeaven.hotelBooking.service.IGuestService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/guest")
public class GuestRestController {

	@Autowired
	private IGuestService guestService;

	//fetches hotel based on the location
	@GetMapping("/hotels")
	@PreAuthorize("hasAuthority('ROLE_GUEST')")
	public List<HotelDTO> searchHotels(@RequestParam String location) {
		return guestService.searchHotels(location);
	}

	
//	@GetMapping("/rooms")
//	public List<RoomDTO> findAvailableRooms(@RequestParam Long hotelId,
//			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkIn,
//			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOut) {
//		return guestService.findAvailableRooms(hotelId, checkIn, checkOut);
//	}

	
	//booking rooms
	@PostMapping("/booking")
	@PreAuthorize("hasAuthority('ROLE_Guest')")
	public BookingDTO bookRoom(@RequestBody BookingDTO bookingDTO) {
		log.info("Rooms booked successfully");
		return guestService.bookRoom(bookingDTO);
	}

	
	//get the booking history
	@PreAuthorize("hasAuthority('ROLE_Guest')")
	@GetMapping("/bookings/{guestId}")
	public List<BookingDTO> getBookingHistory(@PathVariable Long guestId) {
	    return guestService.viewBookingHistory(guestId);
	}

	
	//cancel the booking
	@PutMapping("/booking/cancel/{bookingId}")
	public BookingDTO cancelBooking(@PathVariable Long bookingId) {
		log.info("Booking Cancelled");
		return guestService.cancelBooking(bookingId);
	}
	



}
