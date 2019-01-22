package com.aelastic.xspot.bookings.models;


import com.aelastic.xspot.bookings.models.BookingState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking implements Serializable {

    @NotNull
    @NotEmpty
    private String bookingId;

    @NotNull
    @NotEmpty
    private String userID;

    @NotNull
    @NotEmpty
    private String placeID;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime startDate;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime endDate;

    @NotNull
    @Positive
    private int nrOfPeople;

    private Set<String> friends;

    private BookingState bookingState;

}
