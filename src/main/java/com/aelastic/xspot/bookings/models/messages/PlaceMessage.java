package com.aelastic.xspot.bookings.models.messages;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceMessage {

    private String country;

    private String city;

    private String placeId;

    private String name;

    private int totalNumberOfSeats;

}
