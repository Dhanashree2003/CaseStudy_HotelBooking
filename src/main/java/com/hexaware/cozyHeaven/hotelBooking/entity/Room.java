package com.hexaware.cozyHeaven.hotelBooking.entity;

import com.hexaware.cozyHeaven.hotelBooking.entity.enums.BedType;
import com.hexaware.cozyHeaven.hotelBooking.entity.enums.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "rooms")
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomID;

    @ManyToOne
    @JoinColumn(name = "HotelID")
    private Hotel hotel; // Mapping to Hotel entity

   
    private String roomSize;

    @Enumerated(EnumType.STRING)
    private BedType bedType;

    @NotNull
    private int maxOccupancy;

    @NotNull
    private double baseFare;

    private boolean ac;

    @Enumerated(EnumType.STRING)
    private Status roomStatus;

    
}
