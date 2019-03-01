package com.aelastic.xspot.bookings.models.serdes;

import com.aelastic.xspot.bookings.models.dao.Table;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class TableDeserializer implements Deserializer<Table> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public Table deserialize(String topic, byte[] data) {
        ObjectMapper mapper = new ObjectMapper();
        Table user = null;
        try {
            user = mapper.readValue(data, Table.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }


    @Override
    public void close() {

    }
}
