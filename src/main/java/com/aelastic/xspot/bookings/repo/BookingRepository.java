package com.aelastic.xspot.bookings.repo;

import com.aelastic.xspot.bookings.models.BookingState;
import com.aelastic.xspot.bookings.models.dao.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.stream.Stream;


public interface BookingRepository extends MongoRepository<Booking, String> {

    Stream<Booking> findAllByTableIdAndPlaceIdAndEndDateIsAfterAndStartDateIsBeforeAndBookingState(String id,
                                                                                                   String placeId,
                                                                                                   LocalDateTime startDate,
                                                                                                   LocalDateTime endDate,
                                                                                                   BookingState bookingState);

}
