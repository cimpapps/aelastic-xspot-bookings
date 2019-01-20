package com.aelastic.xspot.bookings.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Place {


    private String country;
    private String city;
    private String placeId;
    private int totalNumberOfSeats;

}
