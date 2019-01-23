package com.aelastic.xspot.bookings.models.mappers;

import com.aelastic.xspot.bookings.models.Constants;
import com.aelastic.xspot.bookings.models.dao.BookingDao;
import com.aelastic.xspot.bookings.models.dto.BookingDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BookingMapper {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);

    public static BookingDto toDto(BookingDao dao) {

        String startDate = dao.getStartDate().format(DATE_TIME_FORMATTER);
        String endDate = dao.getEndDate().format(DATE_TIME_FORMATTER);

        return BookingDto.builder()
                .bookingId(dao.getBookingId())
                .bookingState(dao.getBookingState())
                .userID(dao.getUserID())
                .placeID(dao.getPlaceID())
                .startDate(startDate)
                .endDate(endDate)
                .nrOfPeople(dao.getNrOfPeople())
                .friends(dao.getFriends())
                .build();
    }

    public static BookingDao toDao(BookingDto dto) {

        LocalDateTime startDate = LocalDateTime.parse(dto.getStartDate(), DATE_TIME_FORMATTER);
        LocalDateTime endDate = LocalDateTime.parse(dto.getEndDate(), DATE_TIME_FORMATTER);

        return BookingDao.builder()
                .bookingId(dto.getBookingId())
                .bookingState(dto.getBookingState())
                .userID(dto.getUserID())
                .placeID(dto.getPlaceID())
                .startDate(startDate)
                .endDate(endDate)
                .nrOfPeople(dto.getNrOfPeople())
                .friends(dto.getFriends())
                .build();
    }

}
