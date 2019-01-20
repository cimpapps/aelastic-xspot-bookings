package com.aelastic.xspot.bookings.models.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceResponse {


    private String country;
    private String city;
    private String placeId;
    private int totalNumberOfSeats;

}
