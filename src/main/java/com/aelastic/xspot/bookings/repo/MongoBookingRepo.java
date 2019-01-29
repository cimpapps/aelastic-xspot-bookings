package com.aelastic.xspot.bookings.repo;

import com.aelastic.xspot.bookings.models.dao.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.stream.Stream;


public interface MongoBookingRepo extends MongoRepository<Booking, String> {


    Stream<Booking> findAllByTableId(String id);

    Stream<Booking> findAllByTableIdAndPlaceId(String id, String placeId);
}
