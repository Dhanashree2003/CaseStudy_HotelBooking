package com.hexaware.cozyHeaven.hotelBooking.dto;

import com.hexaware.cozyHeaven.hotelBooking.entity.enums.Role;
import lombok.Data;

@Data
public class UserResponseDTO {
    private Long userID;
    private Role role;
    
    public UserResponseDTO(Long userID, Role role) {
        this.userID = userID;
        this.role = role;
    }
}