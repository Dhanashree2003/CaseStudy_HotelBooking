package com.hexaware.cozyHeaven.hotelBooking.dto;


import com.hexaware.cozyHeaven.hotelBooking.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserSummaryDTO {
    private Long userID;
    private Role role;
}