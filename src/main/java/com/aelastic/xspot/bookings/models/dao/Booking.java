package com.aelastic.xspot.bookings.models.dao;

import com.aelastic.xspot.bookings.models.BookingState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking implements Serializable, Cloneable {

    @Id
    @NotNull
    @NotEmpty
    private String bookingId;

    @NotNull
    @NotEmpty
    private String userId;

    @NotNull
    @NotEmpty
    private String placeId;

    @NotNull
    @NotEmpty
    private String tableId;

    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;

    @NotNull
    @Positive
    private int nrOfPeople;

    private Set<String> friends;

    private BookingState bookingState;

    @Override
    public Booking clone() {
        try {
            return (Booking) super.clone();
        } catch (CloneNotSupportedException e){
            return new Booking();
        }

    }

}