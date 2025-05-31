package com.hexaware.cozyHeaven.hotelBooking.dto;
import com.hexaware.cozyHeaven.hotelBooking.entity.enums.Gender;
import com.hexaware.cozyHeaven.hotelBooking.entity.enums.Role;

import lombok.Data;
@Data
public class UserDTO {
	
	    private Long userID;
	    private String fullName;
	    private String email;
	    private String password;
	    private Role role; // Guest,Owner,Admin
	    private Gender gender;
	    private String contactNumber;
	    private String address;
	    
	

    
}

