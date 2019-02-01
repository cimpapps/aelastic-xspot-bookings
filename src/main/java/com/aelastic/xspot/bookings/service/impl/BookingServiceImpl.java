package com.aelastic.xspot.bookings.service.impl;

import com.aelastic.xspot.bookings.messagebus.outbox.BookingProducer;
import com.aelastic.xspot.bookings.models.BookingState;
import com.aelastic.xspot.bookings.models.dao.Booking;
import com.aelastic.xspot.bookings.models.dao.Table;
import com.aelastic.xspot.bookings.models.request.DeleteBookingRequest;
import com.aelastic.xspot.bookings.models.request.GetBookingByIdRequest;
import com.aelastic.xspot.bookings.models.request.GetBookingsRequest;
import com.aelastic.xspot.bookings.models.request.SaveBookingRequest;
import com.aelastic.xspot.bookings.models.response.DeleteBookingResponse;
import com.aelastic.xspot.bookings.models.response.GetBookingsResponse;
import com.aelastic.xspot.bookings.repo.MongoBookingRepo;
import com.aelastic.xspot.bookings.service.BookingsService;
import com.aelastic.xspot.bookings.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static java.util.concurrent.CompletableFuture.runAsync;

@Service
public class BookingServiceImpl implements BookingsService {

    private MongoBookingRepo mongoBookingRepo;

    private BookingProducer bookingProducer;

    private TableService tableService;

    @Autowired
    public BookingServiceImpl(MongoBookingRepo mongoBookingRepo,
                              BookingProducer bookingProducer,
                              TableService tableService) {
        this.mongoBookingRepo = mongoBookingRepo;
        this.bookingProducer = bookingProducer;
        this.tableService = tableService;
    }


    @Transactional
    @Override
    public Booking saveBooking(SaveBookingRequest bookingRequest) {
        @NotNull Booking booking = bookingRequest.getBooking();

        booking.setBookingState(BookingState.REQUESTED);

        @NotNull Booking save = mongoBookingRepo.save(booking);

        bookingRequest.setBooking(save);

        runAsync(() -> processSaveBookingRequest(bookingRequest));

        return save;
    }


    private void processSaveBookingRequest(@NotNull final SaveBookingRequest bookingRequest) {
        Booking booking = bookingRequest.getBooking();
        List<Table> tables = tableService.findByPlaceAndCapacity(
                booking.getPlaceId(),
                booking.getNrOfPeople());

        tables.stream()
                .filter(t -> !isBooked(t, booking.getStartDate(), booking.getEndDate()))
                .findFirst()
                .ifPresentOrElse(t -> {
                            booking.setTableId(t.getId());
                            booking.setBookingState(BookingState.CONFIRMED);
                        },
                        () -> booking.setBookingState(BookingState.ON_WAITING_LIST));
        bookingProducer.publishBooking(bookingRequest);
    }

    @Transactional
    @Override
    public Booking updateBooking(SaveBookingRequest bookingRequest) {
        //todo implement
        @NotNull Booking booking = bookingRequest.getBooking();

        return booking;
    }

    @Transactional
    @Override
    public DeleteBookingResponse deleteBooking(DeleteBookingRequest deleteBookingRequest) {


        @NotNull String bookingId = deleteBookingRequest.getBookingId();

        Optional<Booking> booking = mongoBookingRepo.findById(bookingId);

        booking.ifPresent(b -> {
            mongoBookingRepo.deleteById(b.getBookingId());
            runAsync(() -> refreshWaitingList(b));
        });
        return DeleteBookingResponse.builder()
                .success(true)
                .requestId(deleteBookingRequest.getRequestId())
                .build();
    }


    @Override
    public Booking getBookingById(GetBookingByIdRequest bookingByIdRequest) {
        String id = bookingByIdRequest.getBookingId();
        return mongoBookingRepo.findById(id).get();
    }

    @Override
    public GetBookingsResponse getBookings(GetBookingsRequest getBookingsRequest) {
        //todo
        return null;
    }


    private void refreshWaitingList(Booking deletedBooking) {
        //TODO

    }


    private boolean isBooked(Table t, LocalDateTime startDate, LocalDateTime endDate) {
        String id = t.getId();
        String placeId = t.getPlaceId();
        Predicate<Booking> isOverlapping = b ->
                (b.getStartDate().isBefore(endDate) && b.getStartDate().isAfter(startDate))
                        || (b.getEndDate().isAfter(startDate) && b.getEndDate().isBefore(endDate)
                        || (b.getStartDate().isBefore(startDate) && b.getEndDate().isAfter(endDate)));


        //TODO after date...
        return mongoBookingRepo.findAllByTableIdAndPlaceId(id, placeId)
                .filter(isOverlapping)
                .findFirst()
                .map(b -> false)
                .orElse(true);
    }


}
