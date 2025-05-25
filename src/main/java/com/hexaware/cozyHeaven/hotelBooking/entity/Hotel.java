package com.hexaware.cozyHeaven.hotelBooking.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotelID;

    @NotBlank
    private String hotelName;

    @NotBlank
    private String location;

    private String amenities;

    @ManyToOne
    @JoinColumn(name = "OwnerID")
    private User owner; // Mapping to User entity

    @OneToMany(mappedBy = "hotel")
    private List<Rooms> rooms; // One hotel can have multiple rooms

    // Getters and Setters
}
