package com.hexaware.cozyHeaven.hotelBooking.dto;

import java.util.List;

import com.hexaware.cozyHeaven.hotelBooking.entity.Room;

import lombok.Data;

@Data
public class HotelDTO {
	
	private Long hotelID;
    private String hotelName;
    private String location;
    private String amenities;
    private Long ownerID; // refers to User
    //private List<Room> room;

}
