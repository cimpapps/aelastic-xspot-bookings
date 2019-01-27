package com.aelastic.xspot.bookings.controllers;

import com.aelastic.xspot.bookings.models.dao.Booking;
import com.aelastic.xspot.bookings.models.request.DeleteBookingRequest;
import com.aelastic.xspot.bookings.models.request.GetBookingByIdRequest;
import com.aelastic.xspot.bookings.models.request.GetBookingsRequest;
import com.aelastic.xspot.bookings.models.request.SaveBookingRequest;
import com.aelastic.xspot.bookings.models.response.DeleteBookingResponse;
import com.aelastic.xspot.bookings.models.response.GetBookingsResponse;
import com.aelastic.xspot.bookings.service.BookingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/bookings")
public class BookingsController {


    private BookingsService bookingsService;

    @Autowired
    public BookingsController(BookingsService bookingsService) {
        this.bookingsService = bookingsService;
    }

    @PostMapping(
            value = "",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Booking> saveBooking(@RequestHeader String requestId,
                                               @RequestBody @Valid Booking booking) {
        Booking savedBooking = bookingsService.saveBooking(SaveBookingRequest.builder()
                .requestId(requestId)
                .booking(booking)
                .build());
        return new ResponseEntity<>(savedBooking, HttpStatus.CREATED);
    }


    @PutMapping(
            value = "",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Booking> updateBooking(@RequestHeader String requestId,
                                                 @RequestBody @Valid Booking booking) throws ChangeSetPersister.NotFoundException {
        Booking savedBooking = bookingsService.updateBooking(SaveBookingRequest.builder()
                .requestId(requestId)
                .booking(booking)
                .build());
        return ResponseEntity.ok(savedBooking);
    }

    @DeleteMapping(value = "",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<DeleteBookingResponse> deleteBooking(@RequestBody @Valid DeleteBookingRequest deleteBookingRequest) {
        DeleteBookingResponse deleteBookingResponse = bookingsService.deleteBooking(deleteBookingRequest);
        return ResponseEntity.ok(deleteBookingResponse);
    }

    @GetMapping(
            value = "/{id}",
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Booking> getBookingById(@RequestHeader String requestId, @PathVariable String id) {
        GetBookingByIdRequest bookingByIdRequest = GetBookingByIdRequest.builder()
                .requestId(requestId)
                .id(id).build();
        Booking booking = bookingsService.getBookingById(bookingByIdRequest);
        return ResponseEntity.ok(booking);
    }

    @GetMapping(
            value = "",
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GetBookingsResponse> getBookings(
            @RequestHeader String requestId,
            @RequestParam int batchSize,
            @RequestParam int batchNr,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String placeId,
            @RequestParam(required = false) String userId) {

        GetBookingsRequest getBookingsRequest = GetBookingsRequest.builder()
                .requestId(requestId)
                .batchSize(batchSize)
                .batchNr(batchNr)
                .startDate(startDate)
                .endDate(endDate)
                .userId(userId)
                .placeId(placeId)
                .build();
        GetBookingsResponse bookingsByUser = bookingsService.getBookings(getBookingsRequest);
        return ResponseEntity.ok(bookingsByUser);
    }


}
