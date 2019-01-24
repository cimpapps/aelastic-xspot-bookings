package com.aelastic.xspot.bookings.models.serdes;

import com.aelastic.xspot.bookings.models.messages.inbox.PlaceMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class PlacesDeserializer implements Deserializer<PlaceMessage> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public PlaceMessage deserialize(String topic, byte[] data) {
        ObjectMapper mapper = new ObjectMapper();
        PlaceMessage user = null;
        try {
            user = mapper.readValue(data, PlaceMessage.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }


    @Override
    public void close() {

    }
}
