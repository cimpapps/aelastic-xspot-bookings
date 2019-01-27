package com.aelastic.xspot.bookings.repo;

import com.aelastic.xspot.bookings.models.dao.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;


public interface MongoBookingRepo extends MongoRepository<Booking, String>, QuerydslPredicateExecutor {



}
