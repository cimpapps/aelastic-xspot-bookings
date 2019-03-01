package com.aelastic.xspot.bookings.models.request;


import com.aelastic.xspot.bookings.models.dao.Booking;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaveBookingRequest implements Serializable {

    @NotNull
    private String requestId;

    @NotNull
    @Valid
    private Booking booking;


}
