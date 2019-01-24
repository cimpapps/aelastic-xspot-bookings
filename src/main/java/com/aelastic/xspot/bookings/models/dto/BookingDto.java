package com.aelastic.xspot.bookings.models.dto;


import com.aelastic.xspot.bookings.models.BookingState;
import com.aelastic.xspot.bookings.models.Constants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingDto implements Serializable {

    @NotNull
    @NotEmpty
    private String bookingId;

    @NotNull
    @NotEmpty
    private String userId;

    @NotNull
    @NotEmpty
    private String placeId;

    @DateTimeFormat(pattern = Constants.DATE_FORMAT)
    private String startDate;

    @DateTimeFormat(pattern = Constants.DATE_FORMAT)
    private String endDate;

    @NotNull
    @Positive
    private int nrOfPeople;

    private Set<String> friends;

    private BookingState bookingState;

}
