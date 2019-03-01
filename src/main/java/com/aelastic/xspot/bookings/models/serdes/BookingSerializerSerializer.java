package com.aelastic.xspot.bookings.models.serdes;

import com.aelastic.xspot.bookings.models.request.SaveBookingRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class BookingSerializerSerializer implements Serializer<SaveBookingRequest> {


    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, SaveBookingRequest data) {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            retVal = objectMapper.writeValueAsString(data).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retVal;

    }

    @Override
    public void close() {





    }
}
