package com.hexaware.cozyHeaven.hotelBooking.service;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.hexaware.cozyHeaven.hotelBooking.repository.BookingRepository;
import com.hexaware.cozyHeaven.hotelBooking.repository.HotelRepository;
import com.hexaware.cozyHeaven.hotelBooking.repository.PaymentRepository;
import com.hexaware.cozyHeaven.hotelBooking.repository.ReviewRepository;
import com.hexaware.cozyHeaven.hotelBooking.repository.RoomRepository;
import com.hexaware.cozyHeaven.hotelBooking.repository.UserRepository;
import com.hexaware.cozyHeaven.hotelBooking.util.MapperUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OwnerServiceImpl implements IOwnerService {

    @Autowired
    private BookingRepository bookingRepo;

    @Autowired
    private RoomRepository roomRepo;

    @Autowired
    private PaymentRepository paymentRepo;

    @Autowired
    private HotelRepository hotelRepo;

    @Autowired
    private ReviewRepository reviewRepo;

    @Autowired
    private UserRepository userRepo;

    @Override
    public List<BookingDTO> getAllBookings(Long ownerId) {
        List<Booking> bookings = bookingRepo.findByRoom_Hotel_Owner_UserID(ownerId);
        return MapperUtil.toBookingDTOList(bookings);
    }

    @Override
    public BookingDTO updateBookingStatus(Long bookingId, BookingDTO bookingDTO) {
        Booking booking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + bookingId));
        booking.setBookingStatus(bookingDTO.getBookingStatus());
        bookingRepo.save(booking);
        return MapperUtil.toBookingDTO(booking);
    }

    @Override
    public List<RoomDTO> getAllRooms(Long hotelID) {
        List<Room> rooms = roomRepo.findByHotel_HotelID(hotelID);
        return MapperUtil.toRoomDTOList(rooms);
    }

    
    //add rooms to hotel by id
    //old
    
//    @Override
//    public RoomDTO addRoom(RoomDTO roomDTO) {
//    	log.info("roomDTO");
//    	 log.info("roomDTO: {}", roomDTO);
//    	log.info("roomDTO");
//        if (roomDTO.getHotelID() == null) {
//            throw new IllegalArgumentException("HotelID cannot be null");
//            
//        }
//
//        Room room = MapperUtil.toRoomEntity(roomDTO);
//
//        Hotel hotel = hotelRepo.findById(roomDTO.getHotelID())
//            .orElseThrow(() -> new RuntimeException("Hotel not found"));
//
//        room.setHotel(hotel);
//        
//        Room savedRoom = roomRepo.save(room);
//        return MapperUtil.toRoomDTO(savedRoom);
//    }

    @Override
    public Room saveRoom(Room room) {
        return roomRepo.save(room);
    }


    
    @Override
    public RoomDTO updateRoom(Long roomId, RoomDTO updatedRoomDTO) {
        Optional<Room> optionalRoom = roomRepo.findById(roomId);
        if (optionalRoom.isEmpty()) {
            throw new RuntimeException("Room not found with ID: " + roomId);
        }

        Room room = optionalRoom.get();

        // Set new values
        room.setRoomName(updatedRoomDTO.getRoomName());
        room.setRoomSize(updatedRoomDTO.getRoomSize());
        room.setBedType(updatedRoomDTO.getBedType());
        room.setMaxOccupancy(updatedRoomDTO.getMaxOccupancy());
        room.setBaseFare(updatedRoomDTO.getBaseFare());
        room.setAc(updatedRoomDTO.isAc());
        room.setRoomStatus(updatedRoomDTO.getRoomStatus());

        // Optionally update hotel if needed
        if (updatedRoomDTO.getHotelID() != null) {
            Hotel hotel = hotelRepo.findById(updatedRoomDTO.getHotelID())
                    .orElseThrow(() -> new RuntimeException("Hotel not found"));
            room.setHotel(hotel);
        }

        Room saved = roomRepo.save(room);
        return MapperUtil.toRoomDTO(saved);
    }

    @Override
    public void deleteRoom(Long roomId) {
        if (!roomRepo.existsById(roomId)) {
            throw new RuntimeException("Room not found with id: " + roomId);
        }
        roomRepo.deleteById(roomId);
    }

    @Override
    public List<PaymentDTO> getAllPayments(Long ownerId) {
        List<Payment> payments = paymentRepo.findByBooking_Room_Hotel_Owner_UserID(ownerId);
        List<PaymentDTO> paymentDTOs = new ArrayList<>();
        for (Payment payment : payments) {
            paymentDTOs.add(MapperUtil.toPaymentDTO(payment));
        }
        return paymentDTOs;
    }

    @Override
    public List<HotelDTO> getHotelsByOwner(Long ownerId) {
        List<Hotel> hotels = hotelRepo.findByOwner_UserID(ownerId);
        List<HotelDTO> hotelDTOs = new ArrayList<>();
        for (Hotel hotel : hotels) {
            hotelDTOs.add(MapperUtil.toHotelDTO(hotel));
        }
        return hotelDTOs;
    }

    @Override
    public List<ReviewDTO> getReviewsByHotel(Long hotelId) {
        List<Review> reviews = reviewRepo.findByHotel_HotelID(hotelId);
        List<ReviewDTO> reviewDTOs = new ArrayList<>();
        for (Review review : reviews) {
            reviewDTOs.add(MapperUtil.toReviewDTO(review));
        }
        return reviewDTOs;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepo.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(MapperUtil.toUserDTO(user));
        }
        return userDTOs;
    }

}
