package com.aelastic.xspot.bookings.models.request;


import com.aelastic.xspot.bookings.models.Booking;
import com.aelastic.xspot.bookings.models.BookingState;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaveBookingRequest implements Serializable {

    @NotNull
    private String requestId;

    @NotNull
    private Booking booking;


}
