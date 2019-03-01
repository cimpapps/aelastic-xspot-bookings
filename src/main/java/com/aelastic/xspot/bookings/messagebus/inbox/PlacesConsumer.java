package com.aelastic.xspot.bookings.messagebus.inbox;

import com.aelastic.xspot.bookings.models.dao.Table;
import com.aelastic.xspot.bookings.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

public class PlacesConsumer {

    private TableService tableService;


    @Autowired
    public PlacesConsumer(TableService tableService) {
        this.tableService = tableService;
    }

    @KafkaListener(topics = "${save_place_topic}")
    public void listenWithHeaders(@Payload Table tableMessage) {

        tableService.saveTable(tableMessage);

    }

}
