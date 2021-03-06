package com.aelastic.xspot.bookings.messagebus.outbox;

import com.aelastic.xspot.bookings.models.dao.Booking;
import com.aelastic.xspot.bookings.models.request.SaveBookingRequest;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Headers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class BookingProducer {


    @Value("${save_booking_topic}")
    private String registerUserTopic;


    private KafkaTemplate<String, Booking> kafkaTemplate;


    @Autowired
    public BookingProducer(KafkaTemplate<String, Booking> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishBooking(SaveBookingRequest bookingMessage) {
        ProducerRecord record = new ProducerRecord<String, Booking>(registerUserTopic, bookingMessage.getBooking());

        record.headers().add("requestId", bookingMessage.getRequestId().getBytes());
        

        kafkaTemplate.send(record);
    }

}
