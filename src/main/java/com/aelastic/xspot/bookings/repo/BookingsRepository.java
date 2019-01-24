package com.aelastic.xspot.bookings.repo;

import com.aelastic.xspot.bookings.models.dao.BookingDao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingsRepository extends MongoRepository<BookingDao, String> {



}
