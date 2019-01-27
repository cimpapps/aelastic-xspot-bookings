package com.aelastic.xspot.bookings.messagebus.inbox;

import com.aelastic.xspot.bookings.models.dao.Table;
import com.aelastic.xspot.bookings.service.TablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

public class PlacesConsumer {

    private TablesService tablesService;


    @Autowired
    public PlacesConsumer(TablesService tablesService) {
        this.tablesService = tablesService;
    }

    @KafkaListener(topics = "${save_place_topic}")
    public void listenWithHeaders(@Payload Table tableMessage) {

        tablesService.savePlace(tableMessage);

    }

}
