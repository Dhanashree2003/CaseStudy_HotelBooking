package com.hexaware.cozyHeaven.hotelBooking.service;


import java.util.List;
import com.hexaware.cozyHeaven.hotelBooking.entity.User;

public interface IUserService {
    User addUser (User user);
    User updateUser (User user);
    User getByUserId(Long userId);
    String deleteByUserId(Long userId);
    List<User> getAllUsers();
}
