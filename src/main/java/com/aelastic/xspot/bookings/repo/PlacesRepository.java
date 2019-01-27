package com.aelastic.xspot.bookings.repo;

import com.aelastic.xspot.bookings.models.dao.Place;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlacesRepository extends MongoRepository<Place, String> {
}
