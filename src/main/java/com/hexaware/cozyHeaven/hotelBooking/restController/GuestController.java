package com.hexaware.cozyHeaven.hotelBooking.restController;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import com.hexaware.cozyHeaven.hotelBooking.dto.BookingDTO;
import com.hexaware.cozyHeaven.hotelBooking.dto.HotelDTO;
import com.hexaware.cozyHeaven.hotelBooking.dto.RoomDTO;
import com.hexaware.cozyHeaven.hotelBooking.service.IGuestService;

@RestController
@RequestMapping("/api/guest")
public class GuestController {

	@Autowired
	private IGuestService guestService;

	@GetMapping("/hotels")
	public List<HotelDTO> searchHotels(@RequestParam String location) {
		return guestService.searchHotels(location);
	}

	@GetMapping("/rooms")
	public List<RoomDTO> findAvailableRooms(@RequestParam Long hotelId,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkIn,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOut) {
		return guestService.findAvailableRooms(hotelId, checkIn, checkOut);
	}

	@PostMapping("/booking")
	public BookingDTO bookRoom(@RequestBody BookingDTO bookingDTO) {
		return guestService.bookRoom(bookingDTO);
	}
//
	@GetMapping("/bookings/{guestId}")
	public List<BookingDTO> getBookingHistory(@PathVariable Long guestId) {
	    return guestService.viewBookingHistory(guestId);
	}


	@PutMapping("/booking/cancel/{bookingId}")
	public BookingDTO cancelBooking(@PathVariable Long bookingId) {
		return guestService.cancelBooking(bookingId);
	}
}
