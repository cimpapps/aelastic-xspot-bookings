package com.aelastic.xspot.bookings.models.mappers;

import com.aelastic.xspot.bookings.models.Constants;
import com.aelastic.xspot.bookings.models.dao.BookingDao;
import com.aelastic.xspot.bookings.models.dto.BookingDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BookingMapper {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);

    public static BookingDto fromDao2Dto(BookingDao dao) {

        String startDate = dao.getStartDate().format(DATE_TIME_FORMATTER);
        String endDate = dao.getEndDate().format(DATE_TIME_FORMATTER);

        return BookingDto.builder()
                .bookingId(dao.getBookingId())
                .bookingState(dao.getBookingState())
                .userId(dao.getUserId())
                .placeId(dao.getPlaceId())
                .startDate(startDate)
                .endDate(endDate)
                .nrOfPeople(dao.getNrOfPeople())
                .friends(dao.getFriends())
                .build();
    }

    public static BookingDao fromDto2Dao(BookingDto dto) {

        LocalDateTime startDate = LocalDateTime.parse(dto.getStartDate(), DATE_TIME_FORMATTER);
        LocalDateTime endDate = LocalDateTime.parse(dto.getEndDate(), DATE_TIME_FORMATTER);

        return BookingDao.builder()
                .bookingId(dto.getBookingId())
                .bookingState(dto.getBookingState())
                .userId(dto.getUserId())
                .placeId(dto.getPlaceId())
                .startDate(startDate)
                .endDate(endDate)
                .nrOfPeople(dto.getNrOfPeople())
                .friends(dto.getFriends())
                .build();
    }

}
