package com.hexaware.cozyHeaven.hotelBooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hexaware.cozyHeaven.hotelBooking.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
