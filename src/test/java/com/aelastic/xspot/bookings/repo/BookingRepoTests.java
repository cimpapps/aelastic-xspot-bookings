package com.aelastic.xspot.bookings.repo;

import com.aelastic.xspot.bookings.models.dao.Booking;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataMongoTest
public class BookingRepoTests {


    private static Booking SAVED_BOOKING;

    private static Booking.BookingBuilder BOOKING_BUILDER;

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private BookingRepository bookingRepo;

    @Before
    public void setup() {
        LocalDateTime time1800 = LocalDateTime.of(2019, 2, 1, 18, 0);
        LocalDateTime time1900 = time1800.plusHours(1);
        BOOKING_BUILDER = Booking.builder()
                .placeId(UUID.randomUUID().toString())
                .startDate(time1800)
                .endDate(time1900)
                .nrOfPeople(7);

        SAVED_BOOKING = bookingRepo.save(BOOKING_BUILDER.build());
    }

    @After
    public void cleanupTest() {
        bookingRepo.deleteAll();
    }


    @Test
    public void testFindById() {

        Optional<Booking> result = bookingRepo.findById(SAVED_BOOKING.getBookingId());
        assertThat(result.get(), equalTo(SAVED_BOOKING));

        Optional<Booking> emptyResult = bookingRepo.findById(UUID.randomUUID().toString());
        assertTrue(emptyResult.isEmpty());

    }

    @Test
    public void testUpdate() {
        Booking b = Booking.builder()
                .bookingId(SAVED_BOOKING.getBookingId())
                .nrOfPeople(2)
                .build();

        bookingRepo.save(b);

        Optional<Booking> result = bookingRepo.findById(SAVED_BOOKING.getBookingId());

        assertThat(result.get(),
                both(equalTo(b))
                        .and(not(equalTo(SAVED_BOOKING))));
    }

    @Test
    public void testFindByPlaceIdAndTableId() {

        String placeId = UUID.randomUUID().toString();
        String tableId = UUID.randomUUID().toString();
        Booking booking1 = BOOKING_BUILDER.placeId(placeId)
                .tableId(tableId).build();
        booking1 = bookingRepo.save(booking1);

        Booking booking2 = BOOKING_BUILDER.tableId(UUID.randomUUID().toString()).build();
        booking2 = bookingRepo.save(booking2);

        Booking booking3 = BOOKING_BUILDER.placeId(UUID.randomUUID().toString()).build();
        booking3 = bookingRepo.save(booking3);

        Stream<Booking> all = bookingRepo.findAllByTableIdAndPlaceId(tableId, placeId);

        assertThat(all.collect(Collectors.toList()),
                both(contains(booking1)).and(not(contains(SAVED_BOOKING, booking2, booking3))));


    }


    @Test
    public void testSaveToAnotherCollection() {
        Booking others_booking = mongoTemplate.save(BOOKING_BUILDER.build(), "others_bookings");
        Optional<Booking> result = bookingRepo.findById(others_booking.getBookingId());
        assertTrue(result.isEmpty());
    }

    @Test
    public void testDeleteBookingByEntity() {
        bookingRepo.delete(SAVED_BOOKING);
        Optional<Booking> result = bookingRepo.findById(SAVED_BOOKING.getBookingId());
        assertTrue(result.isEmpty());
    }

    @Test
    public void testDeleteByEntityMutated() {
        bookingRepo.delete(Booking.builder().bookingId(SAVED_BOOKING.getBookingId()).build());
        Optional<Booking> result = bookingRepo.findById(SAVED_BOOKING.getBookingId());
        assertTrue(result.isEmpty());
    }

    @Test
    public void testDeleteByEntityById() {
        bookingRepo.deleteById(SAVED_BOOKING.getBookingId());
        Optional<Booking> result = bookingRepo.findById(SAVED_BOOKING.getBookingId());
        assertTrue(result.isEmpty());
    }

}
