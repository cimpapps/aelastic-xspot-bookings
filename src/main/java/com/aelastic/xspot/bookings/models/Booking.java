package com.aelastic.xspot.bookings.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking implements Serializable {

    private String userID;

    private String placeID;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private int nrOfPeople;

    private BookingState bookingState;

}
