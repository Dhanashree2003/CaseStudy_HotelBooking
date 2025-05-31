package com.hexaware.cozyHeaven.hotelBooking.util;

import java.util.ArrayList;
import java.util.List;

import com.hexaware.cozyHeaven.hotelBooking.dto.BookingDTO;
import com.hexaware.cozyHeaven.hotelBooking.dto.HotelDTO;
import com.hexaware.cozyHeaven.hotelBooking.dto.RoomDTO;
import com.hexaware.cozyHeaven.hotelBooking.entity.Booking;
import com.hexaware.cozyHeaven.hotelBooking.entity.Hotel;
import com.hexaware.cozyHeaven.hotelBooking.entity.Room;
import com.hexaware.cozyHeaven.hotelBooking.entity.User;

public class MapperUtil {

    public static HotelDTO toHotelDTO(Hotel hotel) {
        HotelDTO dto = new HotelDTO();
        dto.setHotelID(hotel.getHotelID());
        dto.setHotelName(hotel.getHotelName());
        dto.setLocation(hotel.getLocation());
        dto.setOwnerID(hotel.getOwner().getUserID());
        dto.setAmenities(hotel.getAmenities());
        return dto;
    }

    public static RoomDTO toRoomDTO(Room room) {
        RoomDTO dto = new RoomDTO();
        dto.setRoomID(room.getRoomID());
        dto.setHotelID(room.getHotel().getHotelID());
        dto.setRoomSize(room.getRoomSize());
        dto.setBedType(room.getBedType());
        dto.setMaxOccupancy(room.getMaxOccupancy());
        dto.setBaseFare(room.getBaseFare());
        dto.setAc(room.isAc());
        dto.setRoomStatus(room.getRoomStatus());
        return dto;
    }

    public static BookingDTO toBookingDTO(Booking booking) {
        BookingDTO dto = new BookingDTO();
        dto.setBookingID(booking.getBookingID());
        dto.setUserID(booking.getUser().getUserID());
        dto.setRoomID(booking.getRoom().getRoomID());
        dto.setCheckInDate(booking.getCheckInDate());
        dto.setCheckOutDate(booking.getCheckOutDate());
        dto.setNoOfAdults(booking.getNoOfAdults());
        dto.setNoOfChildren(booking.getNoOfChildren());
        dto.setTotalFare(booking.getTotalFare());
        dto.setBookingStatus(booking.getBookingStatus());
        return dto;
    }

    public static Booking toBookingEntity(BookingDTO dto) {
        Booking booking = new Booking();
        booking.setBookingID(dto.getBookingID());
        User user = new User();
        user.setUserID(dto.getUserID());
        booking.setUser(user);
        Room room = new Room();
        room.setRoomID(dto.getRoomID());
        booking.setRoom(room);
        booking.setCheckInDate(dto.getCheckInDate());
        booking.setCheckOutDate(dto.getCheckOutDate());
        booking.setNoOfAdults(dto.getNoOfAdults());
        booking.setNoOfChildren(dto.getNoOfChildren());
        booking.setTotalFare(dto.getTotalFare());
        booking.setBookingStatus(dto.getBookingStatus());
        return booking;
    }

    // List converters using traditional for-loops

    public static List<HotelDTO> toHotelDTOList(List<Hotel> hotels) {
        List<HotelDTO> dtoList = new ArrayList<>();
        for (Hotel hotel : hotels) {
            dtoList.add(toHotelDTO(hotel));
        }
        return dtoList;
    }

    public static List<RoomDTO> toRoomDTOList(List<Room> rooms) {
        List<RoomDTO> dtoList = new ArrayList<>();
        for (Room room : rooms) {
            dtoList.add(toRoomDTO(room));
        }
        return dtoList;
    }

    public static List<BookingDTO> toBookingDTOList(List<Booking> bookings) {
        List<BookingDTO> dtoList = new ArrayList<>();
        for (Booking booking : bookings) {
            dtoList.add(toBookingDTO(booking));
        }
        return dtoList;
    }
}
