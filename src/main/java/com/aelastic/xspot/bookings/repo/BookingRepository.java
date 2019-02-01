package com.aelastic.xspot.bookings.repo;

import com.aelastic.xspot.bookings.models.dao.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.stream.Stream;


public interface BookingRepository extends MongoRepository<Booking, String> {

    Stream<Booking> findAllByTableIdAndPlaceId(String id, String placeId);

}
