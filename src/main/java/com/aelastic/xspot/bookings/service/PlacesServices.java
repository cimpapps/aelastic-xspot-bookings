package com.aelastic.xspot.bookings.service;

import com.aelastic.xspot.bookings.models.dao.PlaceDao;
import com.aelastic.xspot.bookings.models.mappers.PlaceMapper;
import com.aelastic.xspot.bookings.models.messages.inbox.PlaceMessage;
import com.aelastic.xspot.bookings.repo.PlacesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlacesServices {


    private PlacesRepository placesRepository;


    @Autowired
    public PlacesServices(PlacesRepository placesRepository) {
        this.placesRepository = placesRepository;
    }

    @Transactional
    public PlaceDao savePlace(PlaceMessage placeMessage) {
        return placesRepository.save(PlaceMapper.fromMessage2Dao(placeMessage));
    }

}
