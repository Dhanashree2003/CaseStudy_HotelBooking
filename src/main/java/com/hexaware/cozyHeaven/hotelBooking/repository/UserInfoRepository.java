package com.hexaware.cozyHeaven.hotelBooking.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.cozyHeaven.hotelBooking.entity.User;
@Repository
public interface UserInfoRepository extends JpaRepository<User, Long> {
 

//   @Query("SELECT u FROM User u WHERE u.fullName = :username")
//   User findUserByFullName(@Param("username") String username);

   @Query("SELECT u FROM User u WHERE u.email = :email")
   User findUserByEmail(@Param("email") String email);



}