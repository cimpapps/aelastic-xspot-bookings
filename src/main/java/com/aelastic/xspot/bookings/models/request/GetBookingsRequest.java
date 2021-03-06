package com.aelastic.xspot.bookings.models.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetBookingsRequest{

    @NotNull
    private String requestId;

    @NotNull
    private int batchSize;

    @NotNull
    private int batchNr;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private String startDate;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private String endDate;

    private String userId;

    private String placeId;



}