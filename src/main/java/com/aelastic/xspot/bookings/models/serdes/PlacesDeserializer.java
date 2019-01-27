package com.aelastic.xspot.bookings.models.serdes;

import com.aelastic.xspot.bookings.models.dao.Place;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class PlacesDeserializer implements Deserializer<Place> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public Place deserialize(String topic, byte[] data) {
        ObjectMapper mapper = new ObjectMapper();
        Place user = null;
        try {
            user = mapper.readValue(data, Place.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }


    @Override
    public void close() {

    }
}
