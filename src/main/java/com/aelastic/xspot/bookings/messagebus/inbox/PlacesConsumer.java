package com.aelastic.xspot.bookings.messagebus.inbox;

import com.aelastic.xspot.bookings.models.dao.Place;
import com.aelastic.xspot.bookings.service.PlacesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

public class PlacesConsumer {

    private PlacesServices placesServices;


    @Autowired
    public PlacesConsumer(PlacesServices placesServices) {
        this.placesServices = placesServices;
    }

    @KafkaListener(topics = "${save_place_topic}")
    public void listenWithHeaders(@Payload Place placeMessage) {

        placesServices.savePlace(placeMessage);

    }

}
