package com.aelastic.xspot.bookings.models;


import com.aelastic.xspot.bookings.models.BookingState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking implements Serializable {

    private String bookingId;

    private String userID;

    private String placeID;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private int nrOfPeople;

    private Set<String> friends;

    private BookingState bookingState;

}
