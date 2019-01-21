package com.aelastic.xspot.bookings.models.request;


import com.aelastic.xspot.bookings.models.BookingState;
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
public class UpdateBookingRequest implements Serializable {

    private String requestId;

    private String bookingId;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private int nrOfPeople;

    private BookingState bookingState;

}
