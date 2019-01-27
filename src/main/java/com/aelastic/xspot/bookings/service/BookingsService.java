package com.aelastic.xspot.bookings.service;

import com.aelastic.xspot.bookings.models.dao.Booking;
import com.aelastic.xspot.bookings.models.request.DeleteBookingRequest;
import com.aelastic.xspot.bookings.models.request.GetBookingByIdRequest;
import com.aelastic.xspot.bookings.models.request.GetBookingsRequest;
import com.aelastic.xspot.bookings.models.request.SaveBookingRequest;
import com.aelastic.xspot.bookings.models.response.DeleteBookingResponse;
import com.aelastic.xspot.bookings.models.response.GetBookingsResponse;
import org.springframework.stereotype.Service;

@Service
public interface BookingsService {

    Booking saveBooking(SaveBookingRequest build);

    Booking updateBooking(SaveBookingRequest build);

    DeleteBookingResponse deleteBooking(DeleteBookingRequest deleteBookingRequest);

    Booking getBookingById(GetBookingByIdRequest bookingByIdRequest);

    GetBookingsResponse getBookings(GetBookingsRequest getBookingsRequest);
}
