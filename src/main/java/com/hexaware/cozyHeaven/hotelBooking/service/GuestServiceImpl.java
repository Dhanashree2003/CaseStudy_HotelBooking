package com.hexaware.cozyHeaven.hotelBooking.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.cozyHeaven.hotelBooking.dto.BookingDTO;
import com.hexaware.cozyHeaven.hotelBooking.dto.HotelDTO;
import com.hexaware.cozyHeaven.hotelBooking.dto.RoomDTO;
import com.hexaware.cozyHeaven.hotelBooking.entity.Booking;
import com.hexaware.cozyHeaven.hotelBooking.entity.Hotel;
import com.hexaware.cozyHeaven.hotelBooking.entity.Room;
import com.hexaware.cozyHeaven.hotelBooking.entity.enums.BookingStatus;
import com.hexaware.cozyHeaven.hotelBooking.repository.BookingRepository;
import com.hexaware.cozyHeaven.hotelBooking.repository.HotelRepository;
import com.hexaware.cozyHeaven.hotelBooking.repository.RoomRepository;
import com.hexaware.cozyHeaven.hotelBooking.util.MapperUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GuestServiceImpl implements IGuestService {

    @Autowired
    private HotelRepository hotelRepo;

    @Autowired
    private RoomRepository roomRepo;

    @Autowired
    private BookingRepository bookingRepo;

    // Search hotels by location
    @Override
    public List<HotelDTO> searchHotels(String location) {
        List<Hotel> hotels = hotelRepo.findByLocation(location);
        if (hotels.isEmpty()) {
        	log.info("No hotels found at location: {}", location);
            return new ArrayList<>();
        }
        return MapperUtil.toHotelDTOList(hotels);
    }

//    // Find available rooms
//    @Override
//    public List<RoomDTO> findAvailableRooms(Long hotelId, LocalDate checkIn, LocalDate checkOut) {
//        List<Room> rooms = roomRepo.findAvailableRoomsByHotelIdAndDate(hotelId, checkIn, checkOut);
//        return MapperUtil.toRoomDTOList(rooms);
//    }

    //  Calculate total fare
    @Override
    public double calculateTotalFare(RoomDTO room, int numAdults, int numChildren, List<Integer> childrenAges) {
        int totalPeople = numAdults + numChildren;
        int allowed = room.getMaxOccupancy();
        double baseFare = room.getBaseFare();
        double totalFare = baseFare;

        for (int i = 1; i < totalPeople; i++) {
            if (i >= allowed) {
                boolean isAdult = (i - numAdults) >= 0 ? childrenAges.get(i - numAdults) > 14 : true;
                totalFare += baseFare * (isAdult ? 0.40 : 0.20);
            }
        }

        return totalFare;
    }

    // Book room
    @Override
    public BookingDTO bookRoom(BookingDTO bookingDTO) {
        Booking booking = MapperUtil.toBookingEntity(bookingDTO);
        bookingRepo.save(booking);
        log.info("Room booked successfully with ID: {}", booking.getBookingID());
        return bookingDTO;
    }

    // View booking history
    @Override
    public List<BookingDTO> viewBookingHistory(Long userId) {
        List<Booking> bookings = bookingRepo.findBookingsByUserId(userId); 
        return MapperUtil.toBookingDTOList(bookings);
    }


    // Cancel booking
    @Override
    public BookingDTO cancelBooking(Long bookingId) {
        Booking booking = bookingRepo.findById(bookingId).orElse(null);
        if (booking != null) {
        	booking.setBookingStatus(BookingStatus.Cancelled);

            bookingRepo.save(booking);
            return MapperUtil.toBookingDTO(booking);
        }
        return null;
    }
}

