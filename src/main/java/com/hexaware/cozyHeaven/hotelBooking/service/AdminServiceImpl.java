package com.hexaware.cozyHeaven.hotelBooking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.cozyHeaven.hotelBooking.dto.HotelDTO;
import com.hexaware.cozyHeaven.hotelBooking.dto.UserDTO;
import com.hexaware.cozyHeaven.hotelBooking.entity.Hotel;
import com.hexaware.cozyHeaven.hotelBooking.entity.User;
import com.hexaware.cozyHeaven.hotelBooking.entity.enums.Role;
import com.hexaware.cozyHeaven.hotelBooking.exception.ResourceNotFoundException;
import com.hexaware.cozyHeaven.hotelBooking.repository.HotelRepository;
import com.hexaware.cozyHeaven.hotelBooking.repository.UserRepository;
import com.hexaware.cozyHeaven.hotelBooking.util.MapperUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private HotelRepository hotelRepo;


    @Override
    public void deleteUser(Long userId) {
        if (!userRepo.existsById(userId)) {
        	log.warn("User not found with ID: {}", userId);
            throw new ResourceNotFoundException("User not found with ID: " + userId);
        }
        userRepo.deleteById(userId);
    }
    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepo.findByRole(Role.Guest);
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(MapperUtil.toUserDTO(user));
        }
        return userDTOs;
    }

    @Override
    public List<UserDTO> getAllOwners() {
        List<User> owners = userRepo.findByRole(Role.Owner);
        List<UserDTO> ownerDTOs = new ArrayList<>();
        for (User owner : owners) {
            ownerDTOs.add(MapperUtil.toUserDTO(owner));
        }
        return ownerDTOs;
    }

    @Override
    public UserDTO getUserById(Long userId) {
        User user = userRepo.findUserByIdAndRole(userId, Role.Guest);
        		if (user == null) {
        		    throw new ResourceNotFoundException("Guest not found with ID: " + userId);
        		}
        return MapperUtil.toUserDTO(user);
    }

    @Override
    public UserDTO getOwnerById(Long ownerId) {
        User user = userRepo.findUserByIdAndRole(ownerId, Role.Owner);
        if (user == null) {
        		    throw new ResourceNotFoundException("Owner not found with ID: " + ownerId);
        }
        return MapperUtil.toUserDTO(user);
    }

    @Override
    public HotelDTO addHotel(HotelDTO dto) {
        Hotel hotel = new Hotel();
        hotel.setHotelName(dto.getHotelName());
        hotel.setLocation(dto.getLocation());
        hotel.setAmenities(dto.getAmenities());

        User owner = userRepo.findById(dto.getOwnerID())
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found with ID: " + dto.getOwnerID()));
        hotel.setOwner(owner);

        Hotel saved = hotelRepo.save(hotel);
        return MapperUtil.toHotelDTO(saved);
    }

    @Override
    public void deleteHotel(Long hotelId) {
        if (!hotelRepo.existsById(hotelId)) {
            throw new ResourceNotFoundException("Hotel not found with ID: " + hotelId);
        }
        hotelRepo.deleteById(hotelId);
    }

    @Override
    public List<HotelDTO> getAllHotels() {
        return MapperUtil.toHotelDTOList(hotelRepo.findAll());
    }

    @Override
    public HotelDTO getHotelById(Long hotelId) {
        Hotel hotel = hotelRepo.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: " + hotelId));
        return MapperUtil.toHotelDTO(hotel);
    }
}
