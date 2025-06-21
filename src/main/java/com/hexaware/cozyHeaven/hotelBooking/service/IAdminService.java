package com.hexaware.cozyHeaven.hotelBooking.service;

import java.util.List;

import com.hexaware.cozyHeaven.hotelBooking.dto.HotelDTO;
import com.hexaware.cozyHeaven.hotelBooking.dto.UserDTO;
import com.hexaware.cozyHeaven.hotelBooking.entity.Hotel;
import com.hexaware.cozyHeaven.hotelBooking.entity.User;

public interface IAdminService {
	
    // User and Owner
	
	
  // UserDTO addUser(UserDTO userDto);
   void deleteUser(Long userID);
   List<UserDTO> getAllUsers();
   List<UserDTO> getAllOwners();
   UserDTO getUserById(Long userID);
   UserDTO getOwnerById(Long ownerID);

    // Hotel
    Hotel addHotel(HotelDTO hotelDto);
    void deleteHotel(Long hotelID);
    List<HotelDTO> getAllHotels();
    HotelDTO getHotelById(Long hotelID);
	Hotel updateHotel(HotelDTO dto);


}
