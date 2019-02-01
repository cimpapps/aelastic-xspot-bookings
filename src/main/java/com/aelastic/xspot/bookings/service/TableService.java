package com.aelastic.xspot.bookings.service;

import com.aelastic.xspot.bookings.models.dao.Table;

import java.util.List;

public interface TableService {

    Table saveTable(Table tableMessage);

    List<Table> findByPlaceAndCapacity(String placeId, int minCapacity);
}
