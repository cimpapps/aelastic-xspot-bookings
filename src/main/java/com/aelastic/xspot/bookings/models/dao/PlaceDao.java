package com.aelastic.xspot.bookings.models.dao;


import com.aelastic.xspot.bookings.models.messages.inbox.PlaceMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class PlaceDao {

    private String country;

    private String city;

    private String placeId;

    private String name;

    private int totalNumberOfSeats;

}
