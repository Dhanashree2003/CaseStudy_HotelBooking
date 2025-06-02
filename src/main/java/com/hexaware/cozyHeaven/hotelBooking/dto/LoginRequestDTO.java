package com.hexaware.cozyHeaven.hotelBooking.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String email;
    private String password;
}