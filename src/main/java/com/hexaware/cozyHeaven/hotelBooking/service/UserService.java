package com.hexaware.cozyHeaven.hotelBooking.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.cozyHeaven.hotelBooking.entity.User;
import com.hexaware.cozyHeaven.hotelBooking.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;
	
	
	public String addUser(User user) {  // user registration
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
        return "user added to system ";
    }

}