package com.aelastic.xspot.bookings.models.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceRequest {


    private String country;
    private String city;
    private String placeId;
    private int totalNumberOfSeats;

}
