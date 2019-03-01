package com.aelastic.xspot.bookings.service.impl;

import com.aelastic.xspot.bookings.messagebus.outbox.BookingProducer;
import com.aelastic.xspot.bookings.models.dao.Booking;
import com.aelastic.xspot.bookings.repo.BookingRepository;
import com.aelastic.xspot.bookings.service.BookingsService;
import com.aelastic.xspot.bookings.service.TableService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataMongoTest

@EmbeddedKafka
public class BookingServiceImplTests {

    @Autowired
    private BookingRepository bookingRepository;
    @MockBean
    private BookingProducer bookingProducer;
    @Autowired
    private BookingServiceImpl bookingsService;
    @MockBean
    private TableService tableService;

    @Test
    public void testAreOverlapping() {
        LocalDateTime reqStartDate = LocalDateTime.of(2019, 2, 1, 18, 00);
        LocalDateTime reqEndDate = reqStartDate.plusHours(1);

        Booking reqBooking = Booking.builder().startDate(reqStartDate)
                .endDate(reqEndDate).build();
        Booking.BookingBuilder exBooking1 = Booking.builder().startDate(reqStartDate.plusHours(1))
                .endDate(reqStartDate.plusHours(2));

        assertFalse(bookingsService.areOverlapping(reqBooking, exBooking1.build()));

        exBooking1.startDate(reqStartDate.minusMinutes(1));
        assertTrue(bookingsService.areOverlapping(reqBooking, exBooking1.build()));

        exBooking1.startDate(reqEndDate.minusMinutes(1));
        assertTrue(bookingsService.areOverlapping(reqBooking, exBooking1.build()));

        exBooking1.startDate(reqStartDate.plusMinutes(10)).endDate(reqEndDate.minusMinutes(10));
        assertTrue(bookingsService.areOverlapping(reqBooking, exBooking1.build()));

        exBooking1.startDate(reqStartDate.minusHours(2)).endDate(reqStartDate.minusHours(1));
        assertFalse(bookingsService.areOverlapping(reqBooking, exBooking1.build()));

        exBooking1.startDate(reqStartDate.minusHours(2)).endDate(reqStartDate);
        assertFalse(bookingsService.areOverlapping(reqBooking, exBooking1.build()));

        exBooking1.startDate(reqStartDate.minusHours(2)).endDate(reqStartDate.plusMinutes(10));
        assertTrue(bookingsService.areOverlapping(reqBooking, exBooking1.build()));
    }

    @TestConfiguration
    static class TestConfigurationBookings {

        @Bean
        @Autowired
        public BookingsService bookingsService(BookingRepository bookingRepository,
                                               BookingProducer bookingProducer,
                                               TableService tableService) {
            return new BookingServiceImpl(bookingRepository, bookingProducer, tableService);
        }
    }

}
