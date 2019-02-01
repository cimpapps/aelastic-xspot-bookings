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
import com.aelastic.xspot.bookings.repo.BookingRepository;
import com.aelastic.xspot.bookings.service.BookingsService;
import com.aelastic.xspot.bookings.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.concurrent.CompletableFuture.runAsync;

@Service
public class BookingServiceImpl implements BookingsService {

    private BookingRepository bookingRepository;

    private BookingProducer bookingProducer;

    private TableService tableService;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository,
                              BookingProducer bookingProducer,
                              TableService tableService) {
        this.bookingRepository = bookingRepository;
        this.bookingProducer = bookingProducer;
        this.tableService = tableService;
    }


    @Transactional
    @Override
    public Booking saveBooking(SaveBookingRequest bookingRequest) {
        @NotNull Booking booking = bookingRequest.getBooking();

        booking.setBookingState(BookingState.REQUESTED);

        @NotNull Booking save = bookingRepository.save(booking);

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
                .filter(t -> !isBooked(t, booking))
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

        Optional<Booking> booking = bookingRepository.findById(bookingId);

        booking.ifPresent(b -> {
            bookingRepository.deleteById(b.getBookingId());
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
        return bookingRepository.findById(id).get();
    }

    @Override
    public GetBookingsResponse getBookings(GetBookingsRequest getBookingsRequest) {
        //todo
        return null;
    }


    private void refreshWaitingList(Booking deletedBooking) {
        //TODO

    }


    protected boolean isBooked(Table t, Booking requestedBooking) {
        String id = t.getId();
        String placeId = t.getPlaceId();

        //TODO after date...
        return bookingRepository.findAllByTableIdAndPlaceId(id, placeId)
                .filter(existingBooking -> areOverlapping(existingBooking, requestedBooking))
                .findFirst()
                .map(b -> false)
                .orElse(true);
    }

    protected boolean areOverlapping(Booking existingBooking, Booking requestedBooking) {
        @NotNull LocalDateTime exStartDate = existingBooking.getStartDate();
        @NotNull LocalDateTime reqEndDate = requestedBooking.getEndDate();
        @NotNull LocalDateTime reqStartDate = requestedBooking.getStartDate();
        @NotNull LocalDateTime exEndDate = existingBooking.getEndDate();

        return (exStartDate.isBefore(reqEndDate) && exStartDate.isAfter(reqStartDate))
                || (exEndDate.isAfter(reqStartDate) && exEndDate.isBefore(reqEndDate)
                || (exStartDate.isBefore(reqStartDate) && exEndDate.isAfter(reqEndDate)));

    }


}
