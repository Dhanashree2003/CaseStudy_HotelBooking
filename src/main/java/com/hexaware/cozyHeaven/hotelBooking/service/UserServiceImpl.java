package com.hexaware.cozyHeaven.hotelBooking.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hexaware.cozyHeaven.hotelBooking.dto.HotelDTO;
import com.hexaware.cozyHeaven.hotelBooking.entity.User;
import com.hexaware.cozyHeaven.hotelBooking.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

//    @Override
//    public User addUser (User user) {
//        return userRepository.save(user);
//    }

    @Override
    public User updateUser (User user) {
        return userRepository.save(user);
    }

    @Override
    public User getByUserId(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }
    
    
   

//    @Override
//    public String deleteByUserId(Long userId) {
//        userRepository.deleteById(userId);
//        return "User  deleted successfully: " + userId;
//    }
//
//    @Override
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }
}
