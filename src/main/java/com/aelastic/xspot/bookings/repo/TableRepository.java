package com.aelastic.xspot.bookings.repo;

import com.aelastic.xspot.bookings.models.dao.Table;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableRepository extends MongoRepository<Table, String> {

    List<Table> findTableByPlaceIdAndCapacityGreaterThanEqual(String placeId, int capacity);

}
