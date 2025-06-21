package com.hexaware.cozyHeaven.hotelBooking.dto;
//
//import com.hexaware.cozyHeaven.hotelBooking.entity.enums.BedType;
//import com.hexaware.cozyHeaven.hotelBooking.entity.enums.Status;
//
//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;
//import lombok.Data;
//
//@Data
//public class RoomDTO {
//	
//	private Long roomID;
//    private Long hotelID;
//    private String roomSize;
//    private BedType bedType;
//    private int maxOccupancy;
//    private double baseFare;
//    private boolean ac;
//    private Status roomStatus;
//
//}


import com.fasterxml.jackson.annotation.JsonProperty;
import com.hexaware.cozyHeaven.hotelBooking.entity.enums.BedType;
import com.hexaware.cozyHeaven.hotelBooking.entity.enums.Status;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class RoomDTO {

  
    private Long roomID;
    private Long hotelID;
    private String roomName;
    private String roomSize;
    private BedType bedType;
    private int maxOccupancy;
    private double baseFare;
    private boolean ac;
    @Enumerated(EnumType.STRING)
    private Status roomStatus;
}

