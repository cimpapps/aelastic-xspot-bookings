package com.aelastic.xspot.bookings.models.dao;

import com.aelastic.xspot.bookings.models.BookingState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class BookingDao implements Serializable {

    @NotNull
    @NotEmpty
    private String bookingId;

    @NotNull
    @NotEmpty
    private String userId;

    @NotNull
    @NotEmpty
    private String placeId;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @NotNull
    @Positive
    private int nrOfPeople;

    private Set<String> friends;

    private BookingState bookingState;

}