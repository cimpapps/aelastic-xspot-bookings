package com.aelastic.xspot.bookings.controllers;

import com.aelastic.xspot.bookings.models.request.DeleteBookingRequest;
import com.aelastic.xspot.bookings.models.request.GetBookingsRequest;
import com.aelastic.xspot.bookings.models.request.SaveBookingRequest;
import com.aelastic.xspot.bookings.models.Booking;
import com.aelastic.xspot.bookings.models.response.DeleteBookingResponse;
import com.aelastic.xspot.bookings.models.response.GetBookingsResponse;
import com.aelastic.xspot.bookings.service.BookingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/bookings")
public class BookingsController {


    private BookingsService bookingsService;

    @Autowired
    public BookingsController(BookingsService bookingsService) {
        this.bookingsService = bookingsService;
    }

    @PostMapping("")
    public Booking saveBooking(@RequestBody @Valid SaveBookingRequest saveBookingRequest) {
       return bookingsService.saveBooking(saveBookingRequest);
    }

    @PutMapping("")
    public Booking updateBooking(@RequestBody @Valid SaveBookingRequest updateBookingRequest) {
        return bookingsService.saveBooking(updateBookingRequest);
    }

    @DeleteMapping("")
    public DeleteBookingResponse deleteBooking(@RequestBody @Valid DeleteBookingRequest deleteBookingRequest) {
        return bookingsService.deleteBooking(deleteBookingRequest);
    }

    @GetMapping("/users/{userId}")
    public GetBookingsResponse getBookingsByUser(
            @RequestBody @Valid GetBookingsRequest getBookingsRequest,
            @PathVariable String userId)
    {
        return bookingsService.getBookingsByUser(userId, getBookingsRequest);
    }

    @GetMapping("/places/{placeId}")
    public GetBookingsResponse getBookingsByPlace(
            @RequestBody @Valid GetBookingsRequest getBookingsRequest,
            @PathVariable String placeId)
    {
        return bookingsService.getBookingsByPlace(placeId, getBookingsRequest);
    }



}
