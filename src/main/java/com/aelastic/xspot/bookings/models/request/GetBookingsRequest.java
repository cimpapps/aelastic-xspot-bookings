package com.aelastic.xspot.bookings.models.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetBookingsRequest{

    private int requestId;

    private int nrOfPage;

    private String startDate;

    private String endDate;

}