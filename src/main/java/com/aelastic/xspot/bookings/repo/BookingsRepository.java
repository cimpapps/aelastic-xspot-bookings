package com.aelastic.xspot.bookings.repo;

import com.aelastic.xspot.bookings.models.dao.BookingDao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingsRepository extends MongoRepository<BookingDao, String> {


    Optional<List<BookingDao>> findAllBookingsInTimeFrame(String placeId, LocalDateTime startDate, LocalDateTime endDate);
}
