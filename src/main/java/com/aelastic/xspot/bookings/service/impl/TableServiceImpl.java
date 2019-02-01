package com.aelastic.xspot.bookings.service.impl;

import com.aelastic.xspot.bookings.models.dao.Table;
import com.aelastic.xspot.bookings.repo.TableRepository;
import com.aelastic.xspot.bookings.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TableServiceImpl implements TableService {

    private TableRepository tableRepository;

    @Autowired
    public TableServiceImpl(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    @Transactional
    @Override
    public Table saveTable(Table tableMessage) {
        return tableRepository.save(tableMessage);
    }

    @Override
    public List<Table> findByPlaceAndCapacity(String placeId, int minCapacity) {
        return tableRepository.findTableByPlaceIdAndCapacityGreaterThanEqual(placeId, minCapacity);
    }
}
