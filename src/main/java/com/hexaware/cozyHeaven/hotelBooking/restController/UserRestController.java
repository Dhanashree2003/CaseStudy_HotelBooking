package com.hexaware.cozyHeaven.hotelBooking.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.cozyHeaven.hotelBooking.entity.User;
import com.hexaware.cozyHeaven.hotelBooking.service.IUserService;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private IUserService userService;

    @GetMapping("/getall")
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @GetMapping("/getbyid/{userId}")
    public User getById(@PathVariable Long userId) {
        return userService.getByUserId(userId);
    }

    @PostMapping("/add")
    public User addUser (@RequestBody User user) {
        return userService.addUser (user);
    }

    @PutMapping("/update")
    public User updateUser (@RequestBody User user) {
        return userService.updateUser (user);
    }

    @DeleteMapping("/delete/{userId}")
    public String deleteUser (@PathVariable Long userId) {
        return userService.deleteByUserId(userId);
    }
}

