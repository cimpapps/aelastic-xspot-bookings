package com.aelastic.xspot.bookings.models.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteBookingRequest {


    @NotNull
    private String requestId;

    @NotNull
    private String bookingId;

    @NotNull
    private String userId;

}
