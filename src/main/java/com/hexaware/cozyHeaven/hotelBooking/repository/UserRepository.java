package com.hexaware.cozyHeaven.hotelBooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hexaware.cozyHeaven.hotelBooking.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
