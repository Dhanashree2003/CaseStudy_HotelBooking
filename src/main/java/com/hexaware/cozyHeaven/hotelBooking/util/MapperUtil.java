package com.hexaware.cozyHeaven.hotelBooking.util;




import java.util.ArrayList;
import java.util.List;

import com.hexaware.cozyHeaven.hotelBooking.dto.BookingDTO;
import com.hexaware.cozyHeaven.hotelBooking.dto.HotelDTO;
import com.hexaware.cozyHeaven.hotelBooking.dto.PaymentDTO;
import com.hexaware.cozyHeaven.hotelBooking.dto.ReviewDTO;
import com.hexaware.cozyHeaven.hotelBooking.dto.RoomDTO;
import com.hexaware.cozyHeaven.hotelBooking.dto.UserDTO;
import com.hexaware.cozyHeaven.hotelBooking.entity.Booking;
import com.hexaware.cozyHeaven.hotelBooking.entity.Hotel;
import com.hexaware.cozyHeaven.hotelBooking.entity.Payment;
import com.hexaware.cozyHeaven.hotelBooking.entity.Review;
import com.hexaware.cozyHeaven.hotelBooking.entity.Room;
import com.hexaware.cozyHeaven.hotelBooking.entity.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MapperUtil {

    public static HotelDTO toHotelDTO(Hotel hotel) {
        HotelDTO dto = new HotelDTO();
        dto.setHotelID(hotel.getHotelID());
        dto.setHotelName(hotel.getHotelName());
        dto.setLocation(hotel.getLocation());
        dto.setAmenities(hotel.getAmenities());
        dto.setImgUrl(hotel.getImgUrl());
        dto.setOwnerID(hotel.getOwner().getUserID());
        return dto;
    }

    public static RoomDTO toRoomDTO(Room room) {
        RoomDTO dto = new RoomDTO();
        dto.setRoomID(room.getRoomID());
        log.info("mapper "+room.getRoomID());
        dto.setHotelID(room.getHotel().getHotelID());
        log.info("mapper "+room.getHotel().getHotelID());
        dto.setRoomName(room.getRoomName());;
        dto.setRoomSize(room.getRoomSize());
        log.info("mapper "+room.getRoomSize());
        dto.setBedType(room.getBedType());
        log.info("mapper "+room.getBedType());
        dto.setMaxOccupancy(room.getMaxOccupancy());
        log.info("mapper "+room.getMaxOccupancy());
        dto.setBaseFare(room.getBaseFare());
        log.info("mapper "+room.getBaseFare());
        dto.setAc(room.isAc());
        log.info("mapper "+room.isAc());
        dto.setRoomStatus(room.getRoomStatus());
        log.info("mapper "+room.getRoomStatus());
        
        log.info("mapper "+room);
        return dto;
    }

    public static BookingDTO toBookingDTO(Booking booking) {
        BookingDTO dto = new BookingDTO();
        dto.setBookingID(booking.getBookingID());
        dto.setUserID(booking.getUser().getUserID());
        dto.setRoomID(booking.getRoom().getRoomID());
        dto.setHotelID(booking.getHotel().getHotelID());
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
        Hotel hotel = new Hotel();
        hotel.setHotelID(dto.getHotelID());
        booking.setHotel(hotel);
        booking.setCheckInDate(dto.getCheckInDate());
        booking.setCheckOutDate(dto.getCheckOutDate());
        booking.setNoOfAdults(dto.getNoOfAdults());
        booking.setNoOfChildren(dto.getNoOfChildren());
        booking.setTotalFare(dto.getTotalFare());
        booking.setBookingStatus(dto.getBookingStatus());
        return booking;
    }
    
    
    public static Room toRoomEntity(RoomDTO dto) {
    	log.info("mapper1 "+ dto);
        Room room = new Room();
        room.setRoomID(dto.getRoomID());
        room.setRoomName(dto.getRoomName());
        log.info("mapper1 "+ dto);
        room.setRoomSize(dto.getRoomSize());
        log.info("mapper1 "+ dto.getRoomSize());
        room.setBedType(dto.getBedType());
        log.info("mapper1 "+ dto.getBedType());
        room.setMaxOccupancy(dto.getMaxOccupancy());
        log.info("mapper1 "+ dto.getMaxOccupancy());
        room.setBaseFare(dto.getBaseFare());
        log.info("mapper1 "+ dto.getBaseFare());
        room.setAc(dto.isAc());
        log.info("mapper1 "+ dto.isAc());
        room.setRoomStatus(dto.getRoomStatus());
        log.info("mapper1 "+ dto.getRoomStatus());
        

        
        return room;
    }


    public static PaymentDTO toPaymentDTO(Payment payment) {
        PaymentDTO dto = new PaymentDTO();
        dto.setPaymentID(payment.getPaymentID());
        dto.setBookingID(payment.getBooking().getBookingID());
        dto.setPaymentDate(payment.getPaymentDate());
        dto.setAmount(payment.getAmount());
        dto.setPaymentMethod(payment.getPaymentMethod());
        dto.setPaymentStatus(payment.getPaymentStatus());
        return dto;
    }
    
    public static UserDTO toUserDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setUserID(user.getUserID());
        dto.setFullName(user.getFullName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        dto.setGender(user.getGender());
        dto.setContactNumber(user.getContactNumber());
        dto.setAddress(user.getAddress());
        return dto;
    }
    public static User toUserEntity(UserDTO dto) {
        User user = new User();
        user.setUserID(dto.getUserID());
        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setGender(dto.getGender());
        user.setContactNumber(dto.getContactNumber());
        user.setAddress(dto.getAddress());
        return user;
    }

    
    public static ReviewDTO toReviewDTO(Review review) {
        ReviewDTO dto = new ReviewDTO();
        dto.setReviewID(review.getReviewID());
        dto.setUserID(review.getUser().getUserID());
        dto.setHotelID(review.getHotel().getHotelID());
        dto.setRating(review.getRating());
        dto.setComment(review.getComment());
        dto.setReviewDate(review.getReviewDate());
        return dto;
    }
    
    public static Payment toPaymentEntity(PaymentDTO dto) {
        Payment payment = new Payment();
        Booking booking = new Booking();
        booking.setBookingID(dto.getBookingID());
        payment.setBooking(booking);
        payment.setPaymentID(dto.getPaymentID());
        payment.setPaymentDate(dto.getPaymentDate());
        payment.setAmount(dto.getAmount());
        payment.setPaymentMethod(dto.getPaymentMethod());
        payment.setPaymentStatus(dto.getPaymentStatus());
        payment.setTransactionID(dto.getTransactionID());
        payment.setMobileNumber(dto.getMobileNumber());
        payment.setBankName(dto.getBankName());
        return payment;
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
    
    public static List<PaymentDTO> toPaymentDTOList(List<Payment> payments) {
        List<PaymentDTO> dtoList = new ArrayList<>();
        for (Payment payment : payments) {
            dtoList.add(toPaymentDTO(payment));
        }
        return dtoList;
    }
    
    public static List<UserDTO> toUserDTOList(List<User> users) {
        List<UserDTO> dtoList = new ArrayList<>();
        for (User user : users) {
            dtoList.add(toUserDTO(user));
        }
        return dtoList;
    }
        
        public static List<ReviewDTO> toReviewDTOList(List<Review> reviews) {
            List<ReviewDTO> dtoList = new ArrayList<>();
            for (Review review : reviews) {
                dtoList.add(toReviewDTO(review));
            }
            return dtoList;
        }

    
}
