package com.hexaware.cozyHeaven.hotelBooking.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ReviewDTO {
	private Long reviewID;
    private Long userID;
    private Long hotelID;
    private int rating;
    private String comment;
    private LocalDate reviewDate;
	

}
