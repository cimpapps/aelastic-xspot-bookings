package com.aelastic.xspot.bookings.messagebus.outbox;

import com.aelastic.xspot.bookings.models.messages.outbox.BookingMessage;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class BookingProducer {


    @Value("${save_booking_topic}")
    private String registerUserTopic;


    private KafkaTemplate<String, BookingMessage> kafkaTemplate;


    @Autowired
    public BookingProducer(KafkaTemplate<String, BookingMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishBooking(BookingMessage bookingMessage) {
        ProducerRecord record = new ProducerRecord<String, BookingMessage>(registerUserTopic, bookingMessage);
        kafkaTemplate.send(record);
    }

}
