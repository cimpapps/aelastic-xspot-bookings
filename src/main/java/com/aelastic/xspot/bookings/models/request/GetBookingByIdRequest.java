package com.aelastic.xspot.bookings.models.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetBookingByIdRequest {

    private String requestId;

    private String id;

}
