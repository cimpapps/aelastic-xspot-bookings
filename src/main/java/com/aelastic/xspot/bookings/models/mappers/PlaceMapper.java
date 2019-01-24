package com.aelastic.xspot.bookings.models.mappers;

import com.aelastic.xspot.bookings.models.dao.PlaceDao;
import com.aelastic.xspot.bookings.models.messages.inbox.PlaceMessage;

public class PlaceMapper {

    public static PlaceDao fromMessage2Dao(PlaceMessage message) {
        return PlaceDao.builder()
                .country(message.getCountry())
                .city(message.getCity())
                .name(message.getName())
                .placeId(message.getPlaceId())
                .totalNumberOfSeats(message.getTotalNumberOfSeats())
                .build();
    }
    
}
