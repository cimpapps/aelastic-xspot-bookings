package com.aelastic.xspot.bookings.repo;

import com.aelastic.xspot.bookings.models.dao.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingsRepository extends MongoRepository<Booking, String> {


    Optional<List<Booking>> findAllBookingsInTimeFrame(String placeId, LocalDateTime startDate, LocalDateTime endDate);
}
