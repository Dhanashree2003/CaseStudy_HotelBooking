package com.hexaware.cozyHeaven.hotelBooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hexaware.cozyHeaven.hotelBooking.entity.User;
import com.hexaware.cozyHeaven.hotelBooking.entity.enums.Role;

public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByRole(Role role);
	@Query("SELECT u FROM User u WHERE u.userID = :userID AND u.role = :role")
	User findUserByIdAndRole(@Param("userID") Long userID, @Param("role") Role role);

}
