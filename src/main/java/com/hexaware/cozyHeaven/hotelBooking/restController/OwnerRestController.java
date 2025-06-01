package com.hexaware.cozyHeaven.hotelBooking.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.cozyHeaven.hotelBooking.dto.BookingDTO;
import com.hexaware.cozyHeaven.hotelBooking.dto.HotelDTO;
import com.hexaware.cozyHeaven.hotelBooking.dto.PaymentDTO;
import com.hexaware.cozyHeaven.hotelBooking.dto.ReviewDTO;
import com.hexaware.cozyHeaven.hotelBooking.dto.RoomDTO;
import com.hexaware.cozyHeaven.hotelBooking.dto.UserDTO;
import com.hexaware.cozyHeaven.hotelBooking.service.IOwnerService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/owner")
public class OwnerRestController {

    @Autowired
    private IOwnerService ownerService;

    @GetMapping("/bookings")
    public List<BookingDTO> getAllBookings(@RequestParam Long ownerId) {
        return ownerService.getAllBookings(ownerId);
    }

    @PutMapping("/bookings/{bookingId}/status")
    public BookingDTO updateBookingStatus(@PathVariable Long bookingId, @RequestBody BookingDTO bookingDTO) {
        return ownerService.updateBookingStatus(bookingId, bookingDTO);
    }

    @GetMapping("/rooms")
    public List<RoomDTO> getAllRooms(@RequestParam Long ownerId) {
        return ownerService.getAllRooms(ownerId);
    }

    @PostMapping("/rooms")
    public RoomDTO addRoom(@RequestBody RoomDTO roomDTO) {
        return ownerService.addRoom(roomDTO);
    }

    @PutMapping("/rooms/{roomId}")
    public RoomDTO updateRoom(@PathVariable Long roomId, @RequestBody RoomDTO roomDTO) {
        return ownerService.updateRoom(roomId, roomDTO);
    }

    @DeleteMapping("/rooms/{roomId}")
    public void deleteRoom(@PathVariable Long roomId) {
        ownerService.deleteRoom(roomId);
    }

    @GetMapping("/payments")
    public List<PaymentDTO> getAllPayments(@RequestParam Long ownerId) {
        return ownerService.getAllPayments(ownerId);
    }

    @GetMapping("/hotels")
    public List<HotelDTO> getHotelsByOwner(@RequestParam Long ownerId) {
        return ownerService.getHotelsByOwner(ownerId);
    }

    @GetMapping("/reviews")
    public List<ReviewDTO> getReviewsByOwner(@RequestParam Long ownerId) {
        return ownerService.getReviewsByOwner(ownerId);
    }

    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        return ownerService.getAllUsers();
    }
}