package com.hexaware.cozyHeaven.hotelBooking.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.cozyHeaven.hotelBooking.dto.HotelDTO;
import com.hexaware.cozyHeaven.hotelBooking.dto.UserDTO;
import com.hexaware.cozyHeaven.hotelBooking.entity.User;
import com.hexaware.cozyHeaven.hotelBooking.service.IAdminService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {

    @Autowired
    private IAdminService adminService;

    // --- User Management ---
    
    @PostMapping("/add")
    public User addUser (@RequestBody User user) {
        return adminService.addUser (user);
    }
    
//    @PostMapping("/user")
//    public UserDTO addUser(@Valid @RequestBody UserDTO userDto) {
//        return adminService.addUser(userDto);
//    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
    }

    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        return adminService.getAllUsers();
    }

    @GetMapping("/owners")
    public List<UserDTO> getAllOwners() {
        return adminService.getAllOwners();
    }

    @GetMapping("/users/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return adminService.getUserById(id);
    }

    @GetMapping("/owners/{id}")
    public UserDTO getOwnerById(@PathVariable Long id) {
        return adminService.getOwnerById(id);
    }

    // --- Hotel Management ---

    @PostMapping("/hotels")
    public HotelDTO addHotel(@RequestBody HotelDTO hotelDto) {
        return adminService.addHotel(hotelDto);
    }

    @DeleteMapping("/hotels/{id}")
    public void deleteHotel(@PathVariable Long id) {
        adminService.deleteHotel(id);
    }

    @GetMapping("/hotels")
    public List<HotelDTO> getAllHotels() {
        return adminService.getAllHotels();
    }

    @GetMapping("/hotels/{id}")
    public HotelDTO getHotelById(@PathVariable Long id) {
        return adminService.getHotelById(id);
    }
}