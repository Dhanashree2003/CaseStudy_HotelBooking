package com.hexaware.cozyHeaven.hotelBooking.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.cozyHeaven.hotelBooking.dto.AuthRequest;
import com.hexaware.cozyHeaven.hotelBooking.dto.BookingDTO;
import com.hexaware.cozyHeaven.hotelBooking.dto.HotelDTO;
import com.hexaware.cozyHeaven.hotelBooking.dto.PaymentDTO;
import com.hexaware.cozyHeaven.hotelBooking.dto.ReviewDTO;
import com.hexaware.cozyHeaven.hotelBooking.dto.RoomDTO;
import com.hexaware.cozyHeaven.hotelBooking.dto.UserDTO;
import com.hexaware.cozyHeaven.hotelBooking.dto.UserResponseDTO;
import com.hexaware.cozyHeaven.hotelBooking.dto.UserSummaryDTO;
import com.hexaware.cozyHeaven.hotelBooking.entity.Booking;
import com.hexaware.cozyHeaven.hotelBooking.entity.Hotel;
import com.hexaware.cozyHeaven.hotelBooking.entity.Payment;
import com.hexaware.cozyHeaven.hotelBooking.entity.Review;
import com.hexaware.cozyHeaven.hotelBooking.entity.Room;
import com.hexaware.cozyHeaven.hotelBooking.entity.User;
import com.hexaware.cozyHeaven.hotelBooking.entity.enums.BookingStatus;
import com.hexaware.cozyHeaven.hotelBooking.exception.ResourceNotFoundException;
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
public class GuestServiceImpl implements IGuestService {

    @Autowired
    private HotelRepository hotelRepo;

    @Autowired
    private RoomRepository roomRepo;

    @Autowired
    private BookingRepository bookingRepo;
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private PaymentRepository paymentRepo;
    
    
    
        @Autowired
        private ReviewRepository reviewRepo;

        @Override
        public List<ReviewDTO> getReviewsByHotelID(Long hotelID) {
            List<Review> reviews = reviewRepo.findByHotel_HotelID(hotelID);
            return reviews.stream().map(this::convertToDTO).collect(Collectors.toList());
        }

        private ReviewDTO convertToDTO(Review review) {
            ReviewDTO dto = new ReviewDTO();
            dto.setReviewID(review.getReviewID());
            dto.setUserID(review.getUser().getUserID());
            dto.setHotelID(review.getHotel().getHotelID());
            dto.setRating(review.getRating());
            dto.setComment(review.getComment());
            dto.setReviewDate(review.getReviewDate());
            return dto;
        }
        
        @Override
        public Optional<UserDTO> authenticate(AuthRequest request) {
            Optional<User> userOpt = userRepo.findByFullNameAndPassword(
                    request.getEmail(),
                    request.getPassword());

            return userOpt.map(user -> {
                UserDTO dto = new UserDTO();
                dto.setUserID(user.getUserID());
                dto.setRole(user.getRole());
                return dto;
            });
        }
        
        @Override
        public Optional getUserSummaryByEmail(String email) {
            return userRepo.findByEmail(email)
                .map(user -> new UserSummaryDTO(user.getUserID(), user.getRole()));
        }
    

        @Override
        public Set<String> getAllUniqueLocations() {
            List<String> locations = hotelRepo.findDistinctLocations();
            return new HashSet<>(locations); // convert to Set to ensure uniqueness
        }

    public UserResponseDTO getUserByFullNameAndPassword(String fullName, String password) {
        User user = userRepo.findByFullNameAndPassword(fullName, password).orElse(null);

        if (user == null) {
            return null; 
        }

        UserDTO userDTO = MapperUtil.toUserDTO(user);
        log.info("this is "+userDTO.getUserID(), userDTO.getRole());
        return new UserResponseDTO(userDTO.getUserID(), userDTO.getRole());
    }
    
    

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

    // Find available rooms
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
        
   
    @Override
    public List<RoomDTO> getRoomsByHotelID(Long hotelID) {
        List<Room> rooms = roomRepo.findByHotel_HotelID(hotelID);
        return rooms.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private RoomDTO convertToDTO(Room room) {
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
    
    
    @Override
    public ReviewDTO submitReview(ReviewDTO dto) {
        User user = userRepo.findById(dto.getUserID())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Hotel hotel = hotelRepo.findById(dto.getHotelID())
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));

        Review review = new Review();
        review.setUser(user);
        review.setHotel(hotel);
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());
        review.setReviewDate(LocalDate.now());

        return MapperUtil.toReviewDTO(reviewRepo.save(review));
    }
    
    @Override
    public PaymentDTO makePayment(PaymentDTO dto) {
        Optional<Booking> bookingOpt = bookingRepo.findById(dto.getBookingID());
        if (bookingOpt.isEmpty()) {
            throw new RuntimeException("Booking not found for ID: " + dto.getBookingID());
        }

        if (paymentRepo.existsByTransactionID(dto.getTransactionID())) {
            throw new RuntimeException("Transaction ID already used.");
        }


        Payment payment = new Payment();
        payment.setAmount(dto.getAmount());
        payment.setBankName(dto.getBankName());
        payment.setMobileNumber(dto.getMobileNumber());
        payment.setPaymentDate(dto.getPaymentDate());
        payment.setPaymentMethod(dto.getPaymentMethod());
        payment.setPaymentStatus(dto.getPaymentStatus());
        payment.setTransactionID(dto.getTransactionID());
        payment.setBooking(bookingOpt.get());

        Payment saved = paymentRepo.save(payment);

        dto.setPaymentID(saved.getPaymentID());
        return dto;
    }
    
//    @Override
//    public List<Room> getRoomsByAc(boolean ac) {
//        return roomRepo.findByAc(ac);
//    }
//
//    @Override
//    public List<Room> getRoomsByMaxOccupancy(int occupancy) {
//        return roomRepo.findByMaxOccupancyGreaterThanEqual(occupancy);
//    }
//    
//    @Override
//    public List<Room> getAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate) {
//        List<Long> bookedRoomIds = bookingRepo.findBookedRoomIdsBetweenDates(checkInDate, checkOutDate);
//        if (bookedRoomIds.isEmpty()) {
//            return roomRepo.findAll();
//        }
//        return roomRepo.findByRoomIDNotIn(bookedRoomIds);
//    }
    
    
}

