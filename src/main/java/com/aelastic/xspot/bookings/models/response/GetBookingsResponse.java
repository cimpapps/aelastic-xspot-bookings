package com.aelastic.xspot.bookings.models.response;

import com.aelastic.xspot.bookings.models.dao.Booking;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetBookingsResponse {

    private int requestId;
    private List<Booking> bookings;
}
