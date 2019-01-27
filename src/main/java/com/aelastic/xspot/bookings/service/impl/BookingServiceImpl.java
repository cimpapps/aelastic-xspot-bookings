package com.aelastic.xspot.bookings.service.impl;

import com.aelastic.xspot.bookings.messagebus.outbox.BookingProducer;
import com.aelastic.xspot.bookings.models.BookingState;
import com.aelastic.xspot.bookings.models.dao.Booking;
import com.aelastic.xspot.bookings.models.request.DeleteBookingRequest;
import com.aelastic.xspot.bookings.models.request.GetBookingByIdRequest;
import com.aelastic.xspot.bookings.models.request.GetBookingsRequest;
import com.aelastic.xspot.bookings.models.request.SaveBookingRequest;
import com.aelastic.xspot.bookings.models.response.DeleteBookingResponse;
import com.aelastic.xspot.bookings.models.response.GetBookingsResponse;
import com.aelastic.xspot.bookings.repo.MongoBookingRepo;
import com.aelastic.xspot.bookings.repo.MongoTableRepo;
import com.aelastic.xspot.bookings.service.BookingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingsService {

    private MongoBookingRepo mongoBookingRepo;

    private BookingProducer bookingProducer;

    private MongoTableRepo mongoTableRepo;

    @Autowired
    public BookingServiceImpl(MongoBookingRepo mongoBookingRepo,
                              BookingProducer bookingProducer,
                              MongoTableRepo mongoTableRepo) {
        this.mongoBookingRepo = mongoBookingRepo;
        this.bookingProducer = bookingProducer;
        this.mongoTableRepo = mongoTableRepo;
    }

    @Transactional
    @Override
    public Booking saveBooking(SaveBookingRequest bookingRequest) {
        @NotNull Booking booking = bookingRequest.getBooking();
        boolean available = checkAvailability(booking);

        if (available) {
            booking.setBookingState(BookingState.CONFIRMED);
        } else {
            booking.setBookingState(BookingState.ON_WAITING_LIST);
        }

        @NotNull Booking save = mongoBookingRepo.save(booking);
        bookingRequest.setBooking(save);
        bookingProducer.publishBooking(bookingRequest);

        return save;
    }

    @Transactional
    @Override
    public Booking updateBooking(SaveBookingRequest build) throws NotFoundException {
        //todo implement
        @NotNull Booking booking = build.getBooking();
        mongoBookingRepo.deleteById(booking.getBookingId());
        boolean b = checkAvailability(booking);
        if (b) {
            booking.setBookingState(BookingState.CONFIRMED);
        } else {
            throw new NotFoundException();
        }
        return booking;
    }

    @Transactional
    @Override
    public DeleteBookingResponse deleteBooking(DeleteBookingRequest deleteBookingRequest) {


        @NotNull String bookingId = deleteBookingRequest.getBookingId();

        Optional<Booking> booking = mongoBookingRepo.findById(bookingId);

        booking.ifPresent(b -> {
            mongoBookingRepo.deleteById(b.getBookingId());
            refreshWaitingList(b);
        });


        return DeleteBookingResponse.builder()
                .success(true)
                .requestId(deleteBookingRequest.getRequestId())
                .build();
    }


    @Override
    public Booking getBookingById(GetBookingByIdRequest bookingByIdRequest) {

        mongoBookingRepo.findById(bookingByIdRequest.getId());
        return null;
    }

    @Override
    public GetBookingsResponse getBookings(GetBookingsRequest getBookingsRequest) {

        return null;
    }


    private boolean checkAvailability(Booking booking) {

        //TODO implement
        return true;
    }


    private void refreshWaitingList(Booking deletedBooking) {

        List<Booking> waitingList = getWaitingListInTheTimeFrame(deletedBooking.getBookingId(), deletedBooking.getStartDate(), deletedBooking.getEndDate());

        waitingList.stream()
                .filter(b -> b.getNrOfPeople() <= b.getNrOfPeople());
//                .forEach();
        //TODO implement

    }


    private List<Booking> getWaitingListInTheTimeFrame(String bookingId, LocalDateTime startDate, LocalDateTime endDate) {


        //TODO implement
        return null;
    }
}
