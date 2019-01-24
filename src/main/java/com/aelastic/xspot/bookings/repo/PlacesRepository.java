package com.aelastic.xspot.bookings.repo;

import com.aelastic.xspot.bookings.models.dao.PlaceDao;
import com.aelastic.xspot.bookings.models.messages.inbox.PlaceMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlacesRepository extends MongoRepository<PlaceDao, String> {
}
